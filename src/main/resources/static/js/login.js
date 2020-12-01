window.addEventListener('DOMContentLoaded', function () {

    let after_password = document.querySelector("#after_password");
    let register_check = document.querySelector("#register_check");
    let login_form = document.querySelector("#login_form");
    let needchangeemail = document.querySelector("#needchangeemail");

    register_check.addEventListener('click', function (event) {
        if (register_check.checked) {
            login_form.setAttribute("action", "/registration_page");
            needchangeemail.setAttribute("name", "email");
            needchangeemail.setAttribute("type", "email");
            let newDivPhone = createNewDivPhone();
            after_password.after(newDivPhone);
            newDivPhone.append(createNewLabelPhone());
            newDivPhone.append(createPhoneInput());


            let newDivFullname = createNewDivFullname();
            after_password.after(newDivFullname);
            newDivFullname.append(createNewLabelFullname());
            newDivFullname.append(createFullnameInput())
            document.querySelector("#main_f").setAttribute("hidden", "hidden");

        } else {
            login_form.setAttribute("action", "/login_page");
            needchangeemail.setAttribute("name", "username");
            needchangeemail.setAttribute("type", "text");
            document.querySelector("#div_phone").remove();
            document.querySelector("#div_fullname").remove();
            document.querySelector("#main_f").removeAttribute("hidden");
        }
    });

    function createPhoneInput() {
        let input = document.createElement('input');
        input.setAttribute("id", "phone_id")
        input.setAttribute("type", "text");
        input.setAttribute("class", "form-control header_btn_enter text-left popup_engineer_btn");
        input.setAttribute("name", "phone");
        input.setAttribute("maxlength", "12");
        return input;
    }

    function createFullnameInput() {
        let input = document.createElement('input');
        input.setAttribute("id", "fullname_id")
        input.setAttribute("type", "text");
        input.setAttribute("class", "form-control header_btn_enter text-left popup_engineer_btn");
        input.setAttribute("name", "fullName");
        return input;
    }

    function createNewDivFullname() {
        let newDiv = document.createElement('div');
        newDiv.setAttribute("id", "div_fullname")
        newDiv.setAttribute("class", "form-group");
        return newDiv;
    }

    function createNewDivPhone() {
        let newDiv = document.createElement('div');
        newDiv.setAttribute("id", "div_phone")
        newDiv.setAttribute("class", "form-group");
        return newDiv;
    }

    function createNewLabelPhone() {
        let newDiv = document.createElement('label');
        newDiv.setAttribute("id", "label_phone")
        newDiv.setAttribute("for", "phone_id");
        newDiv.setAttribute("class", "header_btn_text");
        newDiv.innerHTML = "Телефон";
        return newDiv;
    }

    function createNewLabelFullname() {
        let newDiv = document.createElement('label');
        newDiv.setAttribute("id", "label_fullname")
        newDiv.setAttribute("for", "fullname_id");
        newDiv.setAttribute("class", "header_btn_text");
        newDiv.innerHTML = "ФИО";

        return newDiv;
    }


});