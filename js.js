$.get('data/contacts.txt').done(function (data) {


    let people = [];
    people = data.split("\n");


    // ON CLICK APPENDS RANDOM PERSON
    $("#box").on("click", function (){
        let random = people[Math.floor(Math.random() * (people.length - 1))];



        $("#description").append(random + `<br>`);
    })


});
