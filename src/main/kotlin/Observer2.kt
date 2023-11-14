class Observer2: IObserver {
    override fun notify(message: String) {
        println("Observer2 received message: $message")
    }
}