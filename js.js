$.get('data/contacts.txt').done(function (data) {


    let people = [];
    people = data.split("\n");


    // ON CLICK APPENDS RANDOM PERSON
    $("#showLine").on("click", function () {
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
        let count = Math.floor(Math.random() * 11);
        console.log(count)
        $("#box").html("")
        $("#box").append(`<div id="person">
            <p id="description"></p>
                <span></span>
    <span></span>
    <span></span>
    <span></span>
        </div>`)

        let random = people[Math.floor(Math.random() * (people.length - 1))];
        console.log(random)
        let split = random.split("|")
        console.log(split)

        $("#description").append(`<p id="name"></i>${split[0]}</p><br><p id="number">${split[1]}</p>`);


        if (count === 0) {
            $("#box").addClass("toggle1")
            $("#person").prepend(`<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSD-l889V8_Nv64SYZECELEBUzvWgmgxdlAow&usqp=CAU">`)
        } else if (count === 1) {
            $("#box").addClass("toggle2")
            $("#person").prepend(`<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTe-nDXn1Xg8qOP0odcLuOkPZ7kpLzeGI-3FQ&usqp=CAU">`)
        } else if (count === 2) {
            $("#box").addClass("toggle3")
            $("#person").prepend(`<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQGqeiMvcMA8ATx6McIgv0QgGq9njL6_9Q9Ww&usqp=CAU">`)
        } else if (count === 3) {
            $("#box").addClass("toggle4")
            $("#person").prepend(`<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSLHvzyqlpe7Aw_qH5ZR5fvjErwjzNuqIlc6A&usqp=CAU">`)
        } else if (count === 4) {
            $("#box").addClass("toggle5")
            $("#person").prepend(`<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT16eO5W8VPjVFrkvG8n_2FQKjByMcbLtBF4A&usqp=CAU">`)
        } else if (count === 5) {
            $("#box").addClass("toggle6")
            $("#person").prepend(`<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6zes53m4a_2VLTcmTn_bHk8NO5SkuWfcQbg&usqp=CAU">`)
        } else if (count === 6) {
            $("#box").addClass("toggle7")
            $("#person").prepend(`<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRsC5vv2K4miQP22QyiC3x6vjLdkmzUITLKQ&usqp=CAU">`)
        } else if (count === 7) {
            $("#box").addClass("toggle8")
            $("#person").prepend(`<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQCT6ifq3Wo5EACfxc3xIeVVdogS_OJsjHOdw&usqp=CAU">`)
        } else if (count === 8) {
            $("#box").addClass("toggle9")
            $("#person").prepend(`<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRmY2DS_XaT4MZa-O3OdDFn78MV4PX_kcU_ag&usqp=CAU">`)
        } else if (count === 9) {
            $("#box").addClass("toggle10")
            $("#person").prepend(`<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSD8197V5L6HbodA7IlH-Rimsb0NRhLhLBEvw&usqp=CAU">`)
        } else if (count === 10) {
            $("#box").addClass("toggle11")
            $("#person").prepend(`<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTo8eAAlNI5l9m4zATOTvt6h8kWbHzZH5fM6g&usqp=CAU">`)
        }
        console.log(count)
        count = 0;

    })


});
