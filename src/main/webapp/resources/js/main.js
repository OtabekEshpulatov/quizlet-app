function update(card_id) {
    fetch('http://localhost:8080/getcards/get/' + card_id)
        .then(response => response.json())
        .then(json => {
            document.getElementById("c_term").value = json.term;
            document.getElementById("c_description").value = json.description;
        });
}