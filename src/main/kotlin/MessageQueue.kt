class MessageQueue {
    var queues = mutableListOf<Queue>()

    init {
        var i=0

        while(true) {
            val queue = Queue(
                subscribers = mutableListOf(),
                messages = mutableListOf()
            )
            queues.add(queue)
            i++
            if(i == 5) break
        }
    }

    fun publish(queueNo: Int, content: String) {
        validate(queueNo)

        queues[queueNo].messages.add(Message(content, false))

        notifyV1()
        removeMessages()
    }

    fun addSubscriber(observer: IObserver, queueNo: Int) {
        validate(queueNo)

        queues[queueNo].subscribers.add(observer)
    }

    fun removeSubscriber(observer: IObserver, queueNo: Int) {
        validate(queueNo)

        queues[queueNo].subscribers.remove(observer)
    }

    private fun validate(queueNo: Int) {
        if(queueNo > queues.size - 1) throw IllegalArgumentException("Queue $queueNo not present")
    }

    private fun notifyV1() {
        queues.forEach { queue ->
            queue.messages.forEach { message ->
                if(!message.isSent) {
                    message.isSent = true
                    queue.subscribers.forEach { subscriber ->
                        subscriber.notify(message.content)
                    }
                }

            }
        }
    }

    private fun removeMessages() {
        queues.forEach { queue ->
            queue.messages = queue.messages.filter { !it.isSent }.toMutableList()
        }
    }
}