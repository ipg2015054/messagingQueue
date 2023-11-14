fun main(args: Array<String>) {
    val messageQueue = MessageQueue()

    // Add an observer
    val observer1 = Observer1()
    messageQueue.addSubscriber(observer1, 0)
    messageQueue.addSubscriber(observer1, 1)
    messageQueue.addSubscriber(observer1, 2)

    messageQueue.publish(0, "first message")
    messageQueue.publish(0, "second message")

    // Add an observer
    val observer2 = Observer1()
    messageQueue.addSubscriber(observer2, 2)
    messageQueue.addSubscriber(observer2, 3)
    messageQueue.addSubscriber(observer2, 4)

    messageQueue.publish(2, "third message")
    messageQueue.publish(4, "fourth message")

    // remove an observer
    messageQueue.removeSubscriber(observer2, 2)
    messageQueue.publish(2, "third message")
}