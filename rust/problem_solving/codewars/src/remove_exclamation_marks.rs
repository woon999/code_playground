pub fn remove_exclamation_marks(input: &str) -> String {
    // input.chars().filter(|&c| c != '!').collect()
    input.replace("!", "")
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_remove_exclamation_marks() {
        assert_eq!(remove_exclamation_marks("Hello, world!"), "Hello, world");
        assert_eq!(remove_exclamation_marks("Hello, world!!"), "Hello, world");
        assert_eq!(remove_exclamation_marks("Hello, world!!!"), "Hello, world");
    }
}