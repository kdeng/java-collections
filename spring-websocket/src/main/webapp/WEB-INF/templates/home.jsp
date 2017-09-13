<!DOCTYPE html>
<html>
<head>
    <title>Train Websocket</title>
    <script type="text/javascript">
        var wsUri = "ws://localhost:8080/ws/echo";
        var output;

        function init() {
            output = document.getElementById("output");
            testWebSocket();
        }

        function testWebSocket() {

            websocket = new WebSocket(wsUri);

            websocket.onopen = function (evt) {
                onOpen(evt)
            };
            websocket.onclose = function (evt) {
                onClose(evt)
            };
            websocket.onmessage = function (evt) {
                onMessage(evt)
            };
            websocket.onerror = function (evt) {
                onError(evt)
            };

        }

        function onOpen(evt) {
            writeToScreen("CONNECTED");
            doSend("HTML5 Java WebSockets Rocks!");
        }
        function onClose(evt) {
            writeToScreen("DISCONNECTED");
        }
        function onMessage(evt) {
            writeToScreen('<span style="color: blue;">RESPONSE:'
                    + evt.data + '</span>');
            //websocket.close();
        }
        function onError(evt) {
            writeToScreen('<span style="color: red;">ERROR:</span>'
                    + evt.data);
        }
        function doSend(message) {
            writeToScreen("SENT: " + message);
            websocket.send(message);
        }


        function writeToScreen(message) {
            var pre = document.createElement("p");
            pre.style.wordWrap = "break-word";
            pre.innerHTML = message;
            output.appendChild(pre);
        }

        window.addEventListener("load", init, false);

    </script>
</head>
<body>
<div id="output" style="width:100%;height:300px;overflow-y: scroll;border:4px solid #333;"></div>
<input type="text" name="message" id="message" value="" />
<input type="button" value="Submit" onclick="javascript:doSend(document.getElementById('message').value);" />
<br />
<input type="button" value="Close" onclick="javascript:doSend('bye-bye');websocket.close();" />
</body>
</html>
