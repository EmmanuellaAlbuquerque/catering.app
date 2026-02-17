class ImageUpload {
    constructor() {
        this.uploadArea = document.getElementById("drop-zone-main");
        this.fileInput = document.getElementById("imageInputMain");
        this.photoGrid = document.getElementById("gallery-main");

        this.init();
    }

    init() {
        this.uploadArea.addEventListener("click", (e) => {
            if (!e.target.closest('.photo-item')) {
                this.fileInput.click();
            }
        });

        this.fileInput.addEventListener("change", () => {
            if (this.fileInput.files.length) {
                this.processUpload(this.fileInput.files);
            }
        });

        this.uploadArea.addEventListener("dragover", (e) => {
            e.preventDefault();
            this.uploadArea.classList.add("is-over");
        });

        ["dragleave", "dragend"].forEach(type => {
            this.uploadArea.addEventListener(type, () =>
                this.uploadArea.classList.remove("is-over")
            );
        });

        this.uploadArea.addEventListener("drop", (e) => {
            e.preventDefault();
            this.uploadArea.classList.remove("is-over");

            if (e.dataTransfer.files.length) {
                this.fileInput.files = e.dataTransfer.files;
                this.processUpload(e.dataTransfer.files);
            }
        });
    }

    processUpload(files) {
        this.photoGrid.innerHTML = "";
        this.uploadArea.classList.add("has-photos");

        Array.from(files).forEach(file => {
            if (!file.type.startsWith("image/")) return;

            const reader = new FileReader();

            reader.onload = (event) => {
                const item = document.createElement("div");
                item.classList.add("photo-item");

                const img = document.createElement("img");
                img.src = event.target.result;

                item.appendChild(img);
                this.photoGrid.appendChild(item);
            };

            reader.readAsDataURL(file);
        });
    }
}
