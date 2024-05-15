use axum::{extract::{Path, Query}, response::{Html, IntoResponse}, routing::{get, get_service}, Router};
use serde::Deserialize;
use tokio::net::TcpListener;
use tower_http::services::ServeDir;

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>>{
    println!("Hello, world!");

    let routes_all: Router = Router::new()
    .merge(routes_hello())
    .fallback_service(routes_static());

    let listener = TcpListener::bind("127.0.0.1:8080").await.unwrap();

    println!("->> LISTENING on {:?}\n", listener.local_addr());
	axum::serve(listener, routes_all.into_make_service())
		.await
		.unwrap();
    
    Ok(())
}


fn routes_static() -> Router {
    let debug = ServeDir::new("./");
    println!("->> {:<12} - routes_static - {debug:?}", "DEBUG");
	Router::new().nest_service("/", get_service(ServeDir::new("./")))
}



// region:    --- Routes Hello
fn routes_hello() -> Router {
	Router::new()
		.route("/hello", get(handler_hello))
		.route("/hello2/:name", get(handler_hello2))
}

#[derive(Debug, Deserialize)]
struct HelloParams {
	name: Option<String>,
}

// e.g., `/hello?name=Jen`
async fn handler_hello(Query(params): Query<HelloParams>) -> impl IntoResponse {
	println!("->> {:<12} - handler_hello - {params:?}", "HANDLER");

	let name = params.name.as_deref().unwrap_or("World!");
	Html(format!("Hello <strong>{name}</strong>"))
}

// e.g., `/hello2/Mike`
async fn handler_hello2(Path(name): Path<String>) -> impl IntoResponse {
	println!("->> {:<12} - handler_hello2 - {name:?}", "HANDLER");

	Html(format!("Hello2 <strong>{name}</strong>"))
}

// endregion: --- Routes Hello