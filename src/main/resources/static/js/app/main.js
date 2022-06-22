let exts = new Set();
let customExtensionCount = 0;
let customExtensionMax = 200;

let main = {
    init: function () {
        $.ajax({
            url: "/list",
            type: "GET",
            dataType: "json"
        }).done(function(json) {
            json.forEach(function(ext) {
                if(ext.fixedYn){
                    let inputFixed;

                    if(ext.useYn){
                        exts.add(ext.name);

                        inputFixed =
                            '<td><div class="form-check"><input class="form-check-input" type="checkbox" onclick="main.check(this)" id="' + ext.name + '" checked>' +
                            '<label class="form-check-label" for="' + ext.name + '">' + ext.name + '</label>' +
                            '</div></td>';
                    } else {
                        inputFixed =
                            '<td><div class="form-check"><input class="form-check-input" type="checkbox" onclick="main.check(this)" id="' + ext.name + '">' +
                            '<label class="form-check-label" for="' + ext.name + '">' + ext.name + '</label>' +
                            '</div></td>';
                    }

                    $("#fixed").append(inputFixed);
                } else {
                    if(ext.useYn) {
                        exts.add(ext.name);

                        let inputExt =
                            '<div style="border: 1px solid; display: inline-block;">'
                            + ext.name +
                            '<i className="close" onClick="main.close(this)"> x</i></div>';

                        $("#exts").append(inputExt);

                        customExtensionCount++;
                    }
                }
            });

            main.count(customExtensionCount);
        });
    },
    add: function () {
        if(customExtensionCount === customExtensionMax){
            alert("최대 추가 개수를 초과하였습니다.");
            return;
        }

        let ext = $("#name").val();

        if(main.isValid(ext) === false){
            alert("1~20자 이내 알파벳 소문자만 입력할 수 있습니다.");
            return;
        }

        if(exts.has(ext)){
            $("#name").val("");
            return;
        }

        exts.add(ext);
        main.create(ext, true, false);

        let inputExt =
            '<div style="border: 1px solid; display: inline-block;">'
            + $("#name").val() +
            '<i className="close" onClick="main.close(this)"> x</i></div>';
        $("#exts").append(inputExt);

        $("#name").val("");

        main.count(++customExtensionCount);
    },
    close: function (e) {
        let ext = e.parentNode.textContent.split(" ")[0];
        exts.delete(ext);
        main.update(ext, false);
        e.parentNode.remove();
        main.count(--customExtensionCount);
    },
    check: function (e) {
        if($("#" + e.id).is(':checked')){
            main.update(e.id, true);
        } else {
            main.update(e.id, false);
        }
    },
    count: function (count) {
        $("#customCount").html(count + "/" + customExtensionMax);
    },
    create: function (name) {
        $.ajax({
            url: "/extension",
            type: "POST",
            dataType: "json",
            contentType: 'application/json',
            data: JSON.stringify({
                "name" : name
            })
        })
    },
    update: function (name, useYn) {
        $.ajax({
            url: "/extension",
            type: "PATCH",
            dataType: "json",
            contentType: 'application/json',
            data: JSON.stringify({
                "name" : name,
                "useYn" : useYn
            })
        })
    },
    isValid: function(name){
        const regex = /^[a-z]{1,20}$/;
        if(!regex.test(name)){
            return false;
        }
    }
};

main.init();