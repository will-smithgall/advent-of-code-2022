function PartOne() {
    var fs = require("fs");

    const data = fs.readFileSync("../data/dayone.txt", "utf8").split("\n");

    let temp = 0;
    let max = 0;

    for (let i = 0; i < data.length; i++) {
        if (data[i] === "") {
            if (temp > max) {
                max = temp;
            }

            temp = 0;
        } else {
            temp += parseInt(data[i]);
        }
    }

    return max;
}

function PartTwo() {
    var fs = require("fs");

    const data = fs.readFileSync("../data/dayone.txt", "utf8").split("\n");

    let temp = 0;
    let first = 0;
    let second = 0;
    let third = 0;

    for (let i = 0; i < data.length; i++) {
        if (data[i] === "") {
            if (temp > first) {
                third = second;
                second = first;
                first = temp;
            } else if (temp > second) {
                third = second;
                second = temp;
            } else if (temp > third) {
                third = temp;
            }

            temp = 0;
        } else {
            temp += parseInt(data[i]);
        }
    }

    if (temp != 0) {
        if (temp > first) {
            third = second;
            second = first;
            first = temp;
        } else if (temp > second) {
            third = second;
            second = temp;
        } else if (temp > third) {
            third = temp;
        }
    }

    return first + second + third;
}

const partOne = PartOne();
const partTwo = PartTwo();

console.log("Part One: " + partOne, "\nPart Two: " + partTwo);
