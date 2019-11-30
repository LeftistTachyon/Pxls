package space.pxls.server

/* Structs */
data class Badge(val displayName: String, val tooltip: String, val type: String, val cssIcon: String? = null)
data class ChatMessage(val nonce: String, val author: String, val date: Long, val message_raw: String, val badges: List<Badge>? = null, val authorNameClass: List<String>? = null, val authorNameColor: Number/*, val message_parsed: String*/)

/* Sent by the client to the server */
/**
 * A data class that represents a chat message sent by a client. This packet travels from the client to the server.
 *
 * @param message the contents of the message
 */
data class ClientChatMessage(val message: String)
class ClientChatHistory()
class ClientChatbanState()
data class ClientUserUpdate(val updates: Map<String,String>)

/* Sent by the server to the client(s) */
data class ServerChatMessage(val message: ChatMessage) {
    val type = "chat_message"
}

data class ServerChatHistory(val messages: List<ChatMessage>) {
    val type = "chat_history";
}

data class ServerChatMessageDelete(val id: Int) {
    val type = "message_delete";
}

data class ServerChatCooldown(val diff: Int, val message: String) {
    val type = "message_cooldown";
}

/**
 * A data class that represents a server chat ban
 *
 * @param permanent whether the ban is permanent
 * @param reason a reason for the chat ban (optional)
 * @param expiry how long it will take to expire (optional)
 * @property type a descriptor for this class's action
 */
data class ServerChatBan(val permanent: Boolean, val reason: String?, val expiry: Long?) {
    val type = "chat_ban"
}

/**
 * A data class that represents the state for a server chat ban (?) or a chat ban (state) (?)
 *
 * @param permanent whether the ban is permanent
 * @param reason a reason for the chat ban (optional)
 * @param expiry how long it will take to expire (optional)
 * @property type a descriptor for this class's action
 */
data class ServerChatbanState(val permanent: Boolean, val reason: String?, val expiry: Long?) {
    val type = "chat_ban_state"
}

/**
 * A data class with a target, initiator, amount (?), and a possible reason
 *
 * @param target the target of the purge
 * @param initiator the initiator of the purge
 * @param amount the amount of messages purged (?)
 * @param reason the reason for the purge, possibly null
 * @property type a descriptor for this class's action
 */
data class ServerChatPurge(val target: String, val initiator: String, val amount: Int, val reason: String?) {
    val type = "chat_purge"
}

/**
 * A data class with a target, initiator, "nonces" (whatever that means), and possibly a reason.
 *
 * @param target the target of the purge
 * @param initiator the initiator of the purge
 * @param nonces a list of Strings for something?
 * @param reason a possibly null String that bears a reason for purge
 * @property type a descriptor for this class's action
 */
data class ServerChatSpecificPurge(val target: String, val initiator: String, val nonces: List<String>, val reason: String?) {
    val type = "chat_purge_specific"
}

data class ServerChatUserUpdate(val who: String, val updates: Map<String,String>) {
    val type = "chat_user_update"
}

data class ServerACKClientUpdate(val success: Boolean, val message: String?, val updateType: String, val updateValue: String?) {
    val type = "ack_client_update"
}