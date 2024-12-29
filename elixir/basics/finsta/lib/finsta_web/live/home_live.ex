defmodule FinstaWeb.HomeLive do
    use FinstaWeb, :live_view
    
    alias Finsta.Posts
    alias Finsta.Posts.Post

    def render(%{loading: true} = assigns) do
        ~H"""
            Finsta is Loading...
        """
    end

    def render(assigns) do
        ~H"""
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <h1 class="text-2xl">Home</h1>
                </div>
            </div>

            <.button type="button" phx-click={show_modal("new-post-modal")}>New Post</.button>

            <div id="feed" phx-update="stream" class="flex flex-col gap-2">
                <div :for={{dom_id, post} <- @streams.posts} id={dom_id} class="w-1/2 mx-auto flex flex-col gap-2 p-4 border rounded">
                    <img src={post.image_path}/>
                    <p><%= post.user.email %></p>
                    <p><%= post.caption %></p>
                </div>
            </div>

            <.modal id="new-post-modal">
                <.simple_form for={@form} phx-change="validate" phx-submit="save-post">
                    <.live_file_input upload={@uploads.image} required />
                    <.input field={@form[:caption]} type="textarea" label="Caption" required />
                    <.button type="submit" phx-disable-with="Saving...">Create Post</.button>
                </.simple_form>
            </.modal>
        </div>
        """
    end 
    
    @impl true
    def mount(_params, _session, socket) do
        if connected?(socket) do
            Phoenix.PubSub.subscribe(Finsta.PubSub, "posts")

            form =
                %Post{}
                |> Post.changeset(%{})
                |> to_form(as: "post")

            socket = 
                socket 
                |> assign(form: form, loading: false)
                |> allow_upload(:image, accept: ~w(.jpg .jpeg .png), max_entries: 1)
                |> stream(:posts, Posts.list_posts())
            {:ok, socket}
        else 
            {:ok, assign(socket, loading: true)}
        end
    end

    @impl true
    def handle_event("validate", _params, socket) do
        {:noreply, socket} 
    end

    @impl true
    def handle_event("save-post", %{"post" => post_params}, socket) do
        %{current_user: user}  = socket.assigns

        post_params
        |> Map.put("user_id", user.id)
        |> Map.put("image_path", List.first(consume_files(socket)))
        |> Posts.save()
        |> case do
            {:ok, post} ->
                socket = 
                    socket
                    |> put_flash(:info, "Post created successfully")
                    |> push_navigate(to: ~p"/home")
                
                Phoenix.PubSub.broadcast(Finsta.PubSub, "posts", {:new, Map.put(post, :user, user)})
                
                {:noreply, socket}
            {:error, _changeset} ->
                {:noreply, socket}
        end
    end

    @impl true
    def handle_info({:new, post}, socket) do
        socket =
            socket 
            |> put_flash(:info, "#{post.user.email} created a new post")
            |> stream_insert(:posts, post, at: 0)

        {:noreply, socket}
    end

    defp consume_files(socket) do
        consume_uploaded_entries(socket, :image, fn %{path: path}, _entry ->
            dest = Path.join([:code.priv_dir(:finsta), "static", "uploads", Path.basename(path)])
            File.cp!(path, dest)
            {:postpone, ~p"/uploads/#{Path.basename(path)}"}
        end)
    end
end