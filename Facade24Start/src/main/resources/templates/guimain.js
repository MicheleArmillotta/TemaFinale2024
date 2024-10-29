var socket;

function connect() {
    // Ottieni l'host e imposta il percorso del WebSocket
    var host = document.location.host;
    var pathname = "/";
    var addr = "ws://" + host + pathname + "WebRobot24Gui";

    // Chiudi la connessione WebSocket esistente, se presente
    if (socket != null) {
        socket.close();
    }

    // Inizializza la nuova connessione WebSocket
    socket = new WebSocket(addr);

    // Gestore per i messaggi WebSocket
    socket.onmessage = function (event) {
        var dataString = event.data;

        // Verifica se il messaggio contiene la mappa (indicato dal carattere "@")
        if (dataString.includes("@")) {
            dataString = dataString.substring(18); // Rimuove il prefisso iniziale
            console.log("ws-status (map): " + dataString);

            // Sostituisce "@" con "<br>" per visualizzare la mappa
            var map = dataString.replaceAll("@", "<br>");
            document.getElementById("map").innerHTML = map;

        } else {
            // Elimina il prefisso iniziale dei dati della tabella
            dataString = dataString.substring(24);
            console.log("ws-status (data): " + dataString);

            // Divide i dati in elementi
            var dataItems = dataString.split("\t");

            // Ottieni la tabella e cancella tutte le righe tranne l'intestazione
            var table = document.getElementById("data-table");
            while (table.rows.length > 1) {
                table.deleteRow(1);
            }

            // Aggiorna la tabella con i nuovi dati
            dataItems.forEach(function (item) {
                var [name, value] = item.split(": ");
                var row = table.insertRow();
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                cell1.textContent = name;
                cell2.textContent = value;
            });
        }
    };

    // Gestore di errore
    socket.onerror = function (error) {
        console.error("WebSocket error:", error);
        document.getElementById("map").textContent = "WebSocket error occurred";
    };

    // Gestore di chiusura
    socket.onclose = function () {
        console.log("WebSocket connection closed");
        document.getElementById("map").textContent = "Disconnected from WebSocket server";
    };
}
