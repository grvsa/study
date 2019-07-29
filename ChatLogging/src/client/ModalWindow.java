package client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import message.Message;
import message.Type;


public class ModalWindow {
    @FXML
    WebView chatArea;
    @FXML
    TextField textField;
    @FXML
    Button btnSend;

    private Controller parent;
    private String nick;
    private WebEngine webEngine;
    private StringBuilder chatStrings;
    public void initialize(){
        chatStrings = new StringBuilder();
        webEngine = chatArea.getEngine();
    }

    public void sendMessage(){
        Message message = new Message(Type.PRIVATECHAT,parent.getNick(),textField.getText());
        message.getDestination().add(nick);
        parent.send(message);
        textField.clear();
        textField.requestFocus();
    }

    public void setParent(Controller parent) {
        this.parent = parent;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void printMessage(Message message){
        final String header =
                "<html>\n" +
                        " <head>\n" +
                        "  <meta charset=\"utf-8\">\n" +
                        "  <title>text-align</title>\n" +
                        "  <style>\n" +
                        "   #left { \n" +
                        "    clear: center;\n" +
                        "    clear: rigth;\n" +
                        "    clear: left;\n" +
                        "    float: left; \n" +
                        "    width: 75%; \n" +
                        "    margin-bottom: 5px;\n" +
                        "    word-wrap: break-word;\n" +
                        "    color: black;\n" +
                        "    background: #e6e7e8;\n" +
                        "    padding-left: 5px;\n" +
                        "    padding-top: 4px;\n" +
                        "    padding-bottom: 4px;\n" +
                        "    padding-right: 5px;\n" +
                        "    border-radius: 5px;\n" +
                        "   }\n" +

                        "   #right { \n" +
                        "    clear: left;\n" +
                        "    clear: center;\n" +
                        "    clear: right;\n" +
                        "    float: right;\n" +
                        "    width: 75%; \n" +
                        "    margin-bottom: 5px;\n" +
                        "    word-wrap: break-word;\n" +
                        "    color: white;\n" +
                        "    background: #7facf5;\n" +
                        "    padding-left: 5px;\n" +
                        "    padding-top: 4px;\n" +
                        "    padding-bottom: 4px;\n" +
                        "    padding-right: 5px;\n" +
                        "    border-radius: 5px;\n" +
                        "   }\n" +
                        "   #center { \n" +
                        "    margin: 0 auto;\n" +
                        "   clear: left;\n" +
                        "   clear: rigth;\n" +
                        "   clear: center;\n" +
                        "   float: left;\n" +
                        "    width: 99%; \n" +
                        "    margin-bottom: 5px;\n" +
                        "    word-wrap: break-word;\n" +
                        "    color: black;\n" +
                        "    background: #b3ffcc;\n" +
                        "    padding-left: 5px;\n" +
                        "    padding-top: 4px;\n" +
                        "    padding-bottom: 4px;\n" +
                        "    padding-right: 5px;\n" +
                        "    border-radius: 5px;\n" +
                        "   }\n" +
                        "   #cont { \n" +
                        "    clear: center;\n" +
                        "    clear: rigth;\n" +
                        "    clear: left;\n" +
                        "    float: left;\n" +
                        "    margin-bottom: 5px;\n" +
                        "    padding-left: 5px;\n" +
                        "    padding-top: 4px;\n" +
                        "    padding-bottom: 4px;\n" +
                        "    padding-right: 5px;\n" +
                        "    border-radius: 5px;\n" +
                        "    width: 10%; \n" +
                        "    color: white; \n" +
                        "   }\n" +
                        "  </style>\n" +
                        "   <script language=\"javascript\" type=\"text/javascript\">\n" +
                        "       function toBottom(){\n" +
                        "           window.scrollTo(0, 10000000 );\n" +
                        "       }\n" +
                        "   </script>\n" +
                        " </head>\n" +
                        " <body onload='toBottom()'>\n";
        final String bottom =
                " </body>\n" +
                        "</html>";


        final String htmlLeft = "<div id=\"left\"><tt>%s</tt></div>\n";
        final String htmlRight = "<div id=\"right\"><tt>%s</tt></div>\n";
        final String htmlCenter = "<div id=\"center\"><tt align=\"center\">%s</tt></div>\n";

        //ToDo вот тут жопа надо исправитькорректно ! стринг билдер
        if (message.getFrom().equals(parent.getNick())){
            chatStrings.append(String.format(htmlRight,message.toString()));
        }else{
            chatStrings.append(String.format(htmlLeft,message.toString()));
        }
        webEngine.loadContent(header + chatStrings.toString() + bottom);
    }
}
