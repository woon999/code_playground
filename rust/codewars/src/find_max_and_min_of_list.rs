pub fn minimum(arr: &[i32]) -> i32 {
    arr.iter().min().unwrap().clone()
}

pub fn maximum(arr: &[i32]) -> i32 {
    arr.iter().max().unwrap().clone()
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_minimum() {
        assert_eq!(minimum(&[1, 2, 3, 4, 5]), 1);
        assert_eq!(minimum(&[10, 20, 30, 40, 50]), 10);
        assert_eq!(minimum(&[5, 4, 3, 2, 1]), 1);
    }

    #[test]
    fn test_maximum() {
        assert_eq!(maximum(&[1, 2, 3, 4, 5]), 5);
        assert_eq!(maximum(&[10, 20, 30, 40, 50]), 50);
        assert_eq!(maximum(&[5, 4, 3, 2, 1]), 5);
    }
}