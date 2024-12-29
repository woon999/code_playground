defmodule ChirpWeb.PostLive.PostComponent do
    use ChirpWeb, :live_component
  
    def render(assigns) do
        ~H"""
        # <div id="post-{@post.id}" class="post">
        #     <div class="row">
        #         <div class="column column-10">
        #             <div class="post-avatar"></div>
        #         </div>
        #         <div class="column column-90 post-body">
        #             <b>@post.username</b>
        #             <br/>
        #             @post.body
        #         </div>       
        #     </div>
        #     <div class="row">
        #         <div class="column">
        #             <i class="far fa-heart"></i> @post.likes_count %>
        #         </div>
        #         <div class="column">
        #             <i class="far fa-retweet"></i> <%= @post.reposts_count %>
        #         </div>
        #         <div class="column">
        #             <!-- If you have any other content to put here, do so -->
        #         </div>
        #     </div>
        # </div>
        """
    end
end
