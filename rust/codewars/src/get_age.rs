pub fn get_age(age: &str)  -> u32 {
    age.split_whitespace().next().unwrap().parse::<u32>().unwrap()
}


#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_get_age() {
        assert_eq!(get_age("2 years old"), 2);
        assert_eq!(get_age("10 years old"), 10);
        assert_eq!(get_age("1 years old"), 1);
    }
}