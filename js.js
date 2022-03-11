$.get('data/contacts.txt').done(function (data) {



    let people = [];
    people = data.split("\n");


    // ON CLICK APPENDS RANDOM PERSON
    $("#showLine").on("click", function () {

        let count = Math.floor(Math.random() * 11);
        console.log(count)
        $("#box").html("")
        $("#box").append(`<div id="person">
            <img id="img" src="" style="visibility: hidden">
            <p id="description"></p>
                <span></span>
    <span></span>
    <span></span>
    <span></span>
        </div>`)



        $("#box").removeClass("toggle1")
        $("#box").removeClass("toggle2")
        $("#box").removeClass("toggle3")
        $("#box").removeClass("toggle4")
        $("#box").removeClass("toggle5")
        $("#box").removeClass("toggle6")
        $("#box").removeClass("toggle7")
        $("#box").removeClass("toggle8")
        $("#box").removeClass("toggle9")
        $("#box").removeClass("toggle10")
        $("#box").removeClass("toggle11")

        if (count === 0) {
            $("#box").addClass("toggle1")
        } else if (count === 1) {
            $("#box").addClass("toggle2")
        } else if (count === 2) {
            $("#box").addClass("toggle3")
        } else if (count === 3) {
            $("#box").addClass("toggle4")
        } else if (count === 4) {
            $("#box").addClass("toggle5")
        } else if (count === 5) {
            $("#box").addClass("toggle6")
        } else if (count === 6) {
            $("#box").addClass("toggle7")
        } else if (count === 7) {
            $("#box").addClass("toggle8")
        } else if (count === 8) {
            $("#box").addClass("toggle9")
        } else if (count === 9) {
            $("#box").addClass("toggle10")
        } else if (count === 10) {
            $("#box").addClass("toggle11")

        }
        console.log(count)
        count = 0;




        let random = people[Math.floor(Math.random() * (people.length - 1))];
        console.log(random)
        let split = random.split("|")
        console.log(split)

        $("#description").append(`<p id="name">OWNER : </i>${split[0]}</p><br><p id="number">CALL TO ADOPT : ${split[1]}</p>`);

        $.get('https://dog.ceo/api/breeds/image/random').done(function (dogs){


            let dog = dogs.message;
            console.log(dogs)
            $("#img").attr("src", dog)
            $("#img").css("visibility", "visible")




        });
    })


});
