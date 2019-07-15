package message;

public enum Type {
    DISCONNECT(1),
    JOIN(2),
    LEFT(3),
    MESSAGE(4),
    AUTHREQUEST(5),
    AUTHOK(6),
    AUTHNG(7),
    PRIVATE(8),
    USERS(9),
    REGISTER(10),
    REGISTEROK(11),
    REGISTERNG(12),
    CHECKLOGIN(13),
    CHECKNICK(14),
    BANLIST(15),
    BANADD(16),
    BANREMOVE(17),
    PRIVATECHAT(18),
    PING(19);

    int value;

    Type(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
