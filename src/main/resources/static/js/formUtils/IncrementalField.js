class IncrementalField {
    constructor(name, label, inputTextContent, type, limit, placeholder, removeButtonText, maxLength = null) {
        this.inputTextContent = inputTextContent;
        this.name = name;
        this.label = label;
        this.type = type;
        this.limit = limit;
        this.placeholder = placeholder;
        this.removeButtonText = removeButtonText;
        this.maxLength = maxLength;
        this.init();
    }

    init() {
        this.list = document.getElementById(`${this.name}-inputs-list`);
        this.addInputButton = document.getElementById(`add-${this.label}`);
        this.addInputButton.addEventListener("click", () => this.addField());
        this.addRemoveButtonToListElements();
        this.updateInputMetadata();
    }

    addField() {
        const index = this.list.querySelectorAll(`.${this.label}-input`).length;
        if (index >= this.limit) return;

        const label = document.createElement("label");
        label.textContent = `${this.inputTextContent} ${index + 1}`;

        const input = document.createElement("input");
        input.type = this.type;
        input.name = `${this.name}[${index}]`;
        input.className = `${this.label}-input`;
        input.placeholder = this.placeholder;
        if (this.maxLength !== null) {
            input.maxLength = this.maxLength;
        }

        const container = document.createElement("div");
        container.className = "input-group-dynamic";
        container.appendChild(label);
        container.appendChild(input);

        this.list.appendChild(container);
        this.addRemoveButton(container);
        this.updateInputMetadata();

        const currentInputs = this.list.querySelectorAll(`.${this.label}-input`).length;
        this.addInputButton.style.display = currentInputs >= this.limit ? "none" : "inline-block";
    }

    addRemoveButtonToListElements() {
        this.list = document.getElementById(`${this.name}-inputs-list`);

        const inputDivs = this.list.querySelectorAll("div");
        inputDivs.forEach(inputDiv => this.addRemoveButton(inputDiv));
    }

    addRemoveButton(inputDiv) {
        const removeBtn = document.createElement("button");
        removeBtn.innerText = this.removeButtonText;
        removeBtn.type = "button";
        removeBtn.className = "btn-remove";
        removeBtn.onclick = () => {
            inputDiv.remove();
            this.updateInputMetadata();
            this.addInputButton.style.display = "inline-block";
        };

        const input = inputDiv.querySelector("input");
        input.insertAdjacentElement("afterend", removeBtn);
    }

    updateInputMetadata() {
        const inputGroups = this.list.querySelectorAll(".input-group-dynamic");
        inputGroups.forEach((inputGroup, index) => {
            const label = inputGroup.querySelector("label");
            const input = inputGroup.querySelector("input");

            label.textContent = `${this.inputTextContent} ${index + 1}`;
            input.name = `${this.name}[${index}]`;
        });

        this.addInputButton.style.display = inputGroups.length < this.limit ? "inline-block" : "none";
    }
}
