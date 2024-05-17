#![allow(unused)] // For beginning only.

use anyhow::Result;
use serde_json::json;

#[tokio::test]
async fn quick_dev() -> Result<()> {
    let hc = httpc_test::new_client("http://localhost:8080")?;

    hc.do_get("/hello").await?.print().await?;
    hc.do_get("/hello2/abcd").await?.print().await?;

    // login 
    hc.do_post("/api/login", json!({
        "username": "demo1",
        "password": "welcome"
    })).await?.print().await?;

    // ticket
    hc.do_post("/api/tickets", json!({
        "title": "Ticket 1"
    })).await?.print().await?;

    // hc.do_delete("/api/tickets/0").await?.print().await?;
    hc.do_get("/api/tickets").await?.print().await?;


    Ok(())
}