class IncrementalField {
    constructor(name, label, inputTextContent, type, limit, placeholder) {
        this.inputTextContent = inputTextContent;
        this.name = name;
        this.label = label;
        this.type = type;
        this.limit = limit;
        this.placeholder = placeholder;
        this.init();
    }

    init() {
        this.list = document.getElementById(`${this.name}-inputs-list`);
        this.addInputButton = document.getElementById(`add-${this.label}`);
        this.addInputButton.addEventListener("click", () => this.addField());
        this.addRemoveButtonToListElements();
    }

    addField() {
        const index = this.list.querySelectorAll(`.${this.label}-input`).length;
        if (index >= this.limit) return;

        const label = document.createElement("label");
        label.textContent = this.inputTextContent + ' ' +  (index + 1);

        const input = document.createElement("input");
        input.type = this.type;
        input.name = `${this.name}[${index}]`;
        input.className = `${this.label}-input`;
        input.placeholder = this.placeholder;

        const phoneContainer = document.createElement("div");
        phoneContainer.className = 'input-group-dynamic';
        phoneContainer.appendChild(label);
        phoneContainer.appendChild(input);

        this.list.appendChild(phoneContainer);
        this.addRemoveButton(phoneContainer);

        const currentInputs = this.list.querySelectorAll(`.${this.label}-input`).length;
        if (currentInputs >= this.limit) {
            this.addInputButton.style.display = "none";
            return;
        }
    }

    addRemoveButtonToListElements() {
        this.list = document.getElementById(`${this.name}-inputs-list`);

        const inputDivs = this.list.querySelectorAll('div');
        inputDivs.forEach(inputDiv => this.addRemoveButton(inputDiv));
    }

    addRemoveButton(inputDiv) {
        const removeBtn = document.createElement('button');
        removeBtn.innerText = 'Remover';
        removeBtn.type = 'button';
        removeBtn.className = 'btn-remove';
        removeBtn.onclick = () => {
            inputDiv.remove();
            removeBtn.remove();
        };
        const input = inputDiv.querySelector('input');
        input.insertAdjacentElement('afterend', removeBtn);
    }
}