const url = "http://localhost:8080/task/user/1"

function hiderLoader() {
    document.getElementById("loading").style.display = "none";
}

function show(tasks) {
    let tab = `<thead>
                <th scope="col">#</th>
                <th scope="col">description</th>
                <th scope="col">Username</th>
                <th scope="col">User Id</th>
                </thead>`;

    for (let task of tasks) {
        tab += `
            <tr>
                <td scope="row">${task.id}</td>
                <td>${task.descricao}</td>
                <td>${task.user.userName}</td>
                <td>${task.user.id}</td>
                </tr>
        `;
    }

    document.getElementById("tasks").innerHTML = tab;
}

async function getApi(url) {
    const response =  await fetch(url, {method: "GET"});

    let data = await response.json();
    console.log(data);
    if(response) {
        hiderLoader();
    } 
    show(data);
}

getApi(url);