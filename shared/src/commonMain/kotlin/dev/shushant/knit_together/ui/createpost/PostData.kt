package dev.shushant.knit_together.ui.createpost


import dev.shushant.network.extensions.generateFullName
import dev.shushant.network.extensions.generateUserId
import kotlin.random.Random

data class PostData(
    val userId: String = generateUserId,
    val userImage: String = "https://i.pravatar.cc/300?u=$userId",
    val userName: String = generateFullName(),
    val bodyText: String? = null,
    val media: List<PostMedia> = listOf(),
    val likeCount: Long = Random.nextLong(),
    val commentCount: Long = Random.nextLong(),
    val isLikedByMe: Boolean = Random.nextBoolean(),
    val likeUsers: List<User> = emptyList()
) {
    data class PostMedia(
        val mediaType: Type = Type.IMAGE,
        val mediaId: String = generateUserId,
        val mediaUrl: MediaType = MediaType.PathType(
            media = AppMedia(
                type = dev.shushant.permission.data.MediaType.PHOTO,
                name = "",
                path = "https://source.unsplash.com/random/?HillStation",
                preview = null
            )
        ),
        val thumbNail: String = "https://source.unsplash.com/random/?HillStation",
    )

    data class User(
        val userName: String = generateFullName(),
        val userId: String = generateUserId,
        val userImage: String = "https://i.pravatar.cc/300?u=$userId",
    )

}

enum class Type {
    IMAGE, VIDEO
}


val postText = listOf(
    "Hey everyone, https://google.com/ I just finished knitting this handmade scarf and I wanted to share my experience with you all!\n" + "\n" + "As a knitter, I love the feeling of creating something beautiful and practical with my own two hands. It's so satisfying to see a project come together stitch by stitch, and to feel the soft wool yarn running through my fingers.\n" + "\n" + "This scarf was a bit of a challenge for me, but I learned so much in the process. I tried out a new stitch pattern and experimented with different color combinations until I found the perfect match.\n" + "\n" + "Now that it's finished, I can't wait to wear it and show it off! It's warm, cozy, and best of all, made with love.\n" + "\n" + "Do you enjoy knitting or any other type of crafting? I'd love to hear about your projects and experiences in the comments below! #knitting #handmade #scarf #crafting #creativity #diy #yarnlove #knittersofinstagram",
    "Hello everyone! https://github.com/ShushantTiwari-ashu I wanted to share my latest knitting project with you. I've been working on this handmade scarf for the past few weeks, and I'm really happy with how it turned out!\n" + "\n" + "As a knitter, I find it so rewarding to create something beautiful and useful with my own two hands. There's something special about the process of knitting, from choosing the perfect yarn to seeing the pattern take shape as you work.\n" + "\n" + "If you're new to knitting, I encourage you to give it a try! It's a great way to unwind and express your creativity. And if you're already a knitter, I'd love to hear about your latest project in the comments below!\n" + "\n" + "Happy knitting, everyone! \uD83E\uDDF6❤️\n" + "\n" + "#knitting #handmade #scarf #knittersofinstagram #creativity #diy #yarnlove",
    "Hey friends! I'm excited to share my latest knitting adventure with you all. I just finished knitting https://github.com/ShushantTiwari-ashu/KnitTogether-CMP my very first sweater, and I couldn't be more proud of myself!\n" + "\n" + "Knitting a sweater was definitely a challenge, but it was so rewarding to see the finished product. I love the feeling of wrapping myself in something that I made with my own two hands. And the best part is that it's so warm and cozy!\n" + "\n" + "For anyone who's thinking about trying to knit a sweater, I say go for it! Yes, it might take some time and effort, but the end result is totally worth it. And don't be afraid to ask for help or guidance along the way - the knitting community is full of helpful and supportive people.\n" + "\n" + "I'm already planning my next sweater project, and I can't wait to see what I come up with. What's the most challenging thing you've ever knitted? Let me know in the comments below!\n" + "\n" + "Happy knitting, everyone! \uD83E\uDDF6❤️\n" + "\n" + "#knitting #sweaterknitting #handmade #diy #knittersofinstagram #yarnlove #knittingcommunity",
    "Hey fellow knitters! I want to take a moment to appreciate all of the amazing things we create with our needles and yarn. Whether you're knitting a cozy sweater, a colorful hat, or a delicate shawl, every stitch is a labor of love.\n" + "\n" + "As a community, we support and inspire each other to keep creating and pushing ourselves to try new things. And that's why I want to hear from YOU! What's one knitting project that you've always wanted to try, but haven't gotten around to yet?\n" + "\n" + "Let's share our ideas and inspire each other to take on new challenges. Who knows, maybe your dream project is just the thing someone else needs to try next.\n" + "\n" + "So let's get chatting in the comments below! And as always, happy knitting! \uD83E\uDDF6❤️\n" + "\n" + "#knittingcommunity #knittersofinstagram #yarnlove #inspiration #creativity #handmade #diy #knittingprojects #challengeyourself"
)


val getCollection = listOf(
    "HillStation",
    "Nature",
    "WildLife",
    "India",
    "Jaipur",
    "Flower",
    "Birds",
    "Mountains",
    "River",
    "Lake"
).random()