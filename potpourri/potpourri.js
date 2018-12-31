function initApplication() {
    console.log('Potpourri EJP - Starting!');
}

function RollSixSidedDice() {
    document.getElementById("dice").value = Math.floor(Math.random()*6) + 1;
}