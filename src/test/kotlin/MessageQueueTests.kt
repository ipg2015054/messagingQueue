import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class MessageQueueTests {
    val messageQueue = MessageQueue.getInstance()

    @Test
    fun test_addSubscriber() {


        val observer1 = Observer1()
        val observer2 = Observer2()

        messageQueue.addSubscriber(observer1, 0)
        messageQueue.addSubscriber(observer2, 0)

        messageQueue.addSubscriber(observer2, 1)

        assertEquals(messageQueue.queues[0].subscribers.size, 2)
        assertEquals(messageQueue.queues[1].subscribers.size, 1)

        assertEquals(messageQueue.queues[1].subscribers[0], observer2)

        messageQueue.removeSubscriber(observer1, 0)
        messageQueue.removeSubscriber(observer2, 0)
        messageQueue.removeSubscriber(observer2, 1)
    }

    @Test
    fun test_removeSubscriber() {
        val messageQueue = MessageQueue.getInstance()

        val observer1 = Observer1()

        messageQueue.addSubscriber(observer1, 0)
        messageQueue.removeSubscriber(observer1, 0)

        assertEquals(messageQueue.queues[0].subscribers.size, 0)
    }

    @Test
    fun test_validation() {
        val messageQueue = MessageQueue.getInstance()

        val observer1 = Observer1()

        assertFails { messageQueue.addSubscriber(observer1, 7) }
    }

    @Test
    fun test_publish() {
        val messageQueue = MessageQueue.getInstance()

        val observer1 = Observer1()

        messageQueue.addSubscriber(observer1, 0)

        messageQueue.publish(0, "A message")

        assertEquals(messageQueue.queues[0].messages.size, 0)

        messageQueue.removeSubscriber(observer1, 0)
    }
}