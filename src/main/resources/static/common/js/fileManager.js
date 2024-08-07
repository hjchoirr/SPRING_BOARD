/**
 * 파일업로드, 삭제, 조회 공통 기능
 */

const fileManager = {
    upload(files, gid, location) {
        try {
            if(!files || files.length == 0) {
                throw new Error("파일을 선택 하세요");
            }
            if(!gid || !gid.trim()) {
                throw new Error("필수항목 누락 (gid)");
            }

            const formData = new FormData();
            formData.append("gid", gid);
            for(const file of files) {
                formData.append("file", file);
            }
            if(location && location.trim()) {
                formData.append("location", location);
            }

            const { ajaxLoad } = commonLib;
            ajaxLoad('/file/upload', 'POST', formData)
                .then(res => {
                    if(!res.success) {
                        alert( res.message);
                        return;
                    }
                    //파일 업로드 후 처리는 다양함, fileUploadCallback 후속 처리 정의
                    if(typeof parent.fileUploadCallback === 'function') {
                        parent.fileUploadCallback(res.data);
                    }
                })
                .catch(err => alert(err.message));


        } catch(e) {
            console.error(e);
            alert( e.message);
        }
    },
    delete() {

    },
    search() {

    }
};

window.addEventListener("DOMContentLoaded", function() {

   const fileUploads = document.getElementsByClassName("fileUploads");
   const fileEl = document.createElement("input");
   fileEl.type = 'file';
   fileEl.multiple = true;

   for(const el of fileUploads) {
       el.addEventListener("click", function () {
           fileEl.value = "";
           delete fileEl.gid;
           delete fileEl.location;

           const dataset = this.dataset;

           console.log("**");
           console.log(dataset);
           fileEl.gid = dataset.gid;
           if(dataset.location) fileEl.location = dataset.location;
           fileEl.click();
       });
   }

   fileEl.addEventListener("change", function (e) {
       const files = e.target.files;
       fileManager.upload(files, fileEl.gid, fileEl.location);
       //console.log(files);
       //console.log(e.target.files);
   });
});