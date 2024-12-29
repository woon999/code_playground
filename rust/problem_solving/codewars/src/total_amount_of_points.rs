fn points(games: &[String]) -> u32 {
    games.iter().map(|x| {
        let v: Vec<&str> = x.split(":").collect();
        let x = v[0].parse::<u32>().unwrap();
        let y = v[1].parse::<u32>().unwrap();
        if x > y {
            3
        } else if x == y {
            1
        } else {
            0
        }
    }).sum()
}


#[cfg(test)]
mod tests{
    use super::*;

    const ERR_MSG: &str = "\nYour result (left) did not match the expected output (right)";
    
    fn do_fixed_test(e: &[&str], expected: u32) {
        let a = &e.iter().map(|x| x.to_string()).collect::<Vec<_>>();
        assert_eq!(points(a), expected, "{ERR_MSG} with games = {a:?}")
    }

    #[test]
    fn test_points() {
        do_fixed_test(&["1:0", "2:0", "3:0", "4:0", "2:1", "3:1", "4:1", "3:2", "4:2", "4:3"], 30);
        do_fixed_test(&["1:1", "2:2", "3:3", "4:4", "2:2", "3:3", "4:4", "3:3", "4:4", "4:4"], 10);
        do_fixed_test(&["0:1", "0:2", "0:3", "0:4", "1:2", "1:3", "1:4", "2:3", "2:4", "3:4"], 0);
    }
}