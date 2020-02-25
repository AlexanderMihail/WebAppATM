<html>
<body>
    <%@ page isELIgnored="false" %>
    <%@ page import="org.example.atm.Database"%>
    <h2>Hi, ${username}</h2>
    Your balance is: ${balance} <br/>
    Your options are:<br/>
    1. <button onClick="deposit()">Deposit:</button> <input id="deposit"><br/>
    2. <button onClick="withdraw()">Withdraw:</button> <input id="withdraw"><br/>
    3. <button onClick="onExit()">EXIT:</button>
</body>
<script>
    function onExit() {
      window.location = "/";
    }
    function deposit() {
      inp = document.getElementById("deposit");
      var value = inp.value;
      insertParam("op", value);
    }
    function withdraw() {
      inp = document.getElementById("withdraw");
      var value = -inp.value;
      insertParam("op", value);
    }
    function insertParam(key, value) {
        key = escape(key); value = escape(value);
        var kvp = document.location.search.substr(1).split('&');
        if (kvp == '')
            document.location.search = '?' + key + '=' + value;
        else {
            var i = kvp.length; var x; while (i--) {
                x = kvp[i].split('=');
                if (x[0] == key) {
                    x[1] = value;
                    kvp[i] = x.join('=');
                    break;
                }
            }
            if (i < 0) { kvp[kvp.length] = [key, value].join('='); }
            //this will reload the page, it's likely better to store this until finished
            document.location = document.location.origin + "/operation?" + kvp.join('&');
        }
    }
    </script>
</html>
