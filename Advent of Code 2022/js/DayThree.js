function PartOne() {
    var fs = require("fs");

    const data = fs.readFileSync("../data/daythree.txt", "utf8").split("\n");

    let priority = 0;

    for (let i = 0; i < data.length; i++) {
        let firstHalf = data[i].substring(0, data[i].length / 2);
        let secondHalf = data[i].substring(data[i].length / 2);

        for (let j = 0; j < firstHalf.length; j++) {
            if (secondHalf.includes(firstHalf.substring(j, j + 1))) {
                priority += Priority(firstHalf.substring(j, j + 1));
                break;
            }
        }
    }

    return priority;
}

function PartTwo() {
    var fs = require("fs");

    const data = fs.readFileSync("../data/daythree.txt", "utf8").split("\n");

    let priority = 0;

    for (let i = 0; i < data.length; i += 3) {
        for (let j = 0; j < data[i].length; j++) {
            if (
                data[i + 1].includes(data[i].substring(j, j + 1)) &&
                data[i + 2].includes(data[i].substring(j, j + 1))
            ) {
                priority += Priority(data[i].substring(j, j + 1));
                break;
            }
        }
    }

    return priority;
}

function Priority(letter) {
    const alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    return alphabet.indexOf(letter) + 1;
}

const partOne = PartOne();
const partTwo = PartTwo();

console.log("Part One: " + partOne, "\nPart Two: " + partTwo);
