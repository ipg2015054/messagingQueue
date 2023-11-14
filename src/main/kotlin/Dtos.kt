data class Queue(
    val subscribers: MutableList<IObserver>,
    var messages: MutableList<Message>
)

data class Message(
    val content: String,
    var isSent: Boolean
)