const fileUp = {
    func1() {
        alert("fileUp!! - func1()");

    }
}

window.addEventListener("DOMContentLoaded", function() {
    //alert("fileUp.js")
    //fileUp.func1();
    const input = document.createElement("input");
    input.type = "file";
    input.multiple = true;

    const els = document.getElementsByClassName("fileUploads");
    for(const el of els) {
        el.addEventListener("click", function() {
            //alert(el.type + " is clicked!! " );

            input.click();
        });
    }
    input.addEventListener("click", function() {
        //alert("input file button clicked!!")

    })
});

