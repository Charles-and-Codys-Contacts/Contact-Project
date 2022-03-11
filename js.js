$.get('data/contacts.txt').done(function (data) {


    let people = [];
    people = data.split("\n");


    // ON CLICK APPENDS RANDOM PERSON
    $("#showLine").on("click", function (){
        let count = Math.floor(Math.random() * 2);

        if (count %2 == 0){
            $("#box").html("")
            $("#box").append(`<div id="person">
            <p id="description"></p>
        </div>`)
            $("#box").toggleClass("toggle1")
            let random = people[Math.floor(Math.random() * (people.length - 1))];
            $("#person").prepend(`<img src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png">`)
            $("#description").append(random + `<br>`);

        }else {
            $("#box").html("")
            $("#box").append(`<div id="person">
            <p id="description"></p>
        </div>`)
            $("#box").toggleClass("toggle2")
            let random = people[Math.floor(Math.random() * (people.length - 1))];
            $("#person").prepend(`<img src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png">`)
            $("#description").append(random + `<br>`);

        }


    })


});
