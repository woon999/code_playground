use axum::{extract::{Path, Query}, middleware, response::{Html, IntoResponse, Response}, routing::{get, get_service}, Router};
use serde::Deserialize;
use tokio::net::TcpListener;
use tower_cookies::CookieManagerLayer;
use tower_http::services::ServeDir;
use crate::model::ModelController;

mod model;
mod web;
mod error;

pub use self::error::{Error, Result};



#[tokio::main]
async fn main() -> Result<()>{
    println!("Hello, world!");
    let mc = ModelController::new().await?;

    let routes_apis = web::routes_ticket::routes(mc.clone());
        
    let routes_all: Router = Router::new()
        .merge(routes_hello())
        .merge(web::routes_login::routes())
        .nest("/api", routes_apis)
        .layer(middleware::map_response(response_mapper))
        .layer(CookieManagerLayer::new())
        .fallback_service(routes_static());

    let listener = TcpListener::bind("127.0.0.1:8080").await.unwrap();

    println!("->> LISTENING on {:?}\n", listener.local_addr());
	axum::serve(listener, routes_all.into_make_service())
		.await
		.unwrap();
    
    Ok(())
}

async fn response_mapper(res: Response) -> Response {
    println!("->> {:<12} - response_mapper", "RES_MAPPER");

    println!();

    res
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