function flatten(arr) {
    let result = [];

    arr.forEach((element) => {
        if (Array.isArray(element)) {
            result = result.concat(flatten(element));
        } else {
            result.push(element);
        }
    });

    return result;
}

const testCases = [
    [1, [2, [3, 4], 5], 6],
    [[7, 8], [9, 10], [11, [12, 13]]],
    [14, [15, [16, [17, 18]]], 19],
    [[[14, [15, [16, [17, 18]]], 19, [[[20]]]]]],
];

// Loop through test cases and output flattened arrays
testCases.forEach((testCase, index) => {
    const flattenedArray = flatten(testCase);
    console.log(`Test case ${index + 1}:`, flattenedArray);
});
