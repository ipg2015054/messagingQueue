class Observer1: IObserver {
    override fun notify(message: String) {
        println("Observer1 recieved message: $message")
    }
}