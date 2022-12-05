function PartOne() {
    var fs = require("fs");

    const data = fs.readFileSync("../data/daytwo.txt", "utf8").split("\r\n");

    let totalPoints = 0;

    for (let i = 0; i < data.length; i++) {
        let roundPoints = 0;

        const moves = data[i].split(" ");

        if (moves[0] === "A") {
            if (moves[1] === "X") {
                roundPoints += 4;
            } else if (moves[1] === "Y") {
                roundPoints += 8;
            } else if (moves[1] === "Z") {
                roundPoints += 3;
            }
        } else if (moves[0] === "B") {
            if (moves[1] === "X") {
                roundPoints += 1;
            } else if (moves[1] === "Y") {
                roundPoints += 5;
            } else if (moves[1] === "Z") {
                roundPoints += 9;
            }
        } else if (moves[0] === "C") {
            if (moves[1] === "X") {
                roundPoints += 7;
            } else if (moves[1] === "Y") {
                roundPoints += 2;
            } else if (moves[1] === "Z") {
                roundPoints += 6;
            }
        }

        totalPoints += roundPoints;
    }

    return totalPoints;
}

function PartTwo() {
    let fs = require("fs");

    const data = fs.readFileSync("../data/daytwo.txt", "utf8").split("\r\n");

    let totalPoints = 0;

    for (let i = 0; i < data.length; i++) {
        let roundPoints = 0;

        const moves = data[i].split(" ");

        if (moves[0] === "A") {
            if (moves[1] === "X") {
                roundPoints += 3;
            } else if (moves[1] === "Y") {
                roundPoints += 4;
            } else if (moves[1] === "Z") {
                roundPoints += 8;
            }
        } else if (moves[0] === "B") {
            if (moves[1] === "X") {
                roundPoints += 1;
            } else if (moves[1] === "Y") {
                roundPoints += 5;
            } else if (moves[1] === "Z") {
                roundPoints += 9;
            }
        } else if (moves[0] === "C") {
            if (moves[1] === "X") {
                roundPoints += 2;
            } else if (moves[1] === "Y") {
                roundPoints += 6;
            } else if (moves[1] === "Z") {
                roundPoints += 7;
            }
        }

        totalPoints += roundPoints;
    }

    return totalPoints;
}

const partOne = PartOne();
const partTwo = PartTwo();

console.log("Part One: " + partOne, "\nPart Two: " + partTwo);
