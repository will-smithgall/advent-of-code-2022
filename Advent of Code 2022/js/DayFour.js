function PartOne() {
    var fs = require("fs");

    const data = fs.readFileSync("../data/dayfour.txt", "utf8").split("\n");

    let count = 0;

    for (let i = 0; i < data.length; i++) {
        const values = data[i].split("-").join(",").split(",");

        if (
            parseInt(values[0]) >= parseInt(values[2]) &&
            parseInt(values[1]) <= parseInt(values[3])
        ) {
            count += 1;
        } else if (
            parseInt(values[0]) <= parseInt(values[2]) &&
            parseInt(values[1]) >= parseInt(values[3])
        ) {
            count += 1;
        }
    }

    return count;
}

function PartTwo() {
    var fs = require("fs");

    const data = fs.readFileSync("../data/dayfour.txt", "utf8").split("\n");

    let count = 0;

    for (let i = 0; i < data.length; i++) {
        const values = data[i].split("-").join(",").split(",");

        if (
            parseInt(values[0]) >= parseInt(values[2]) &&
            parseInt(values[1]) <= parseInt(values[3])
        ) {
            count += 1;
        } else if (
            parseInt(values[0]) <= parseInt(values[2]) &&
            parseInt(values[1]) >= parseInt(values[3])
        ) {
            count += 1;
        } else if (
            parseInt(values[0]) <= parseInt(values[3]) &&
            parseInt(values[0]) >= parseInt(values[2])
        ) {
            count += 1;
        } else if (
            parseInt(values[1]) >= parseInt(values[2]) &&
            parseInt(values[1]) <= parseInt(values[3])
        ) {
            count += 1;
        }
    }

    return count;
}

const partOne = PartOne();
const partTwo = PartTwo();

console.log(
    "Overlapping ID Total: " + partOne,
    "\nPartially Overlapping ID Total: " + partTwo
);
