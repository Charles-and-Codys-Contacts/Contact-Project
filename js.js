$.get('data/contacts.txt').done(function (data) {


    let people = [];
    people = data.split("\n");


    // ON CLICK APPENDS RANDOM PERSON
    $("#showLine").on("click", function (){
        let random = people[Math.floor(Math.random() * people.length)];
        $("#description").append(random + `<br>`);
    })


});