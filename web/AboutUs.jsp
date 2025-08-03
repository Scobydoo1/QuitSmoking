<!DOCTYPE html>
<html lang="en">
    <%@ page contentType="text/html; charset=UTF-8" language="java" %>
    <head>
        <meta charset="UTF-8">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>About Us - BBFB</title>
        <link rel="stylesheet" href="css/StyleAboutUs.css">
        <link href="css/stylehomepage.css" rel="stylesheet" type="text/css"/>

        <%@include file="information/bootstrap.jspf" %>
    </head>

    <body>
        <%@include file="information/header.jspf" %>

        <main>
            <section class="about-us-blur">
                <div class="overlay">
                    <h2>About Us</h2>
                    <p>Sứ mệnh của BBFB là hỗ trợ mọi người từ bỏ thuốc lá và hướng đến một cuộc sống khỏe mạnh,
                        hạnh phúc hơn thông qua các chương trình cá nhân hóa và cộng đồng hỗ trợ tích cực.</p>
                </div>
            </section>

            <section class="mission">

                <div class="image">
                    <img src="images/aboutUs/anhh.png" alt="Community">
                </div>
                <div class="content">
                    <h3>Bạn sẽ nhận được gì khi tham gia cùng chúng tôi?</h3>
                    <ol>
                        <li><strong>Kế hoạch cai thuốc cá nhân hóa:</strong> Chúng tôi xây dựng lộ trình riêng dựa trên thói quen hút thuốc, tình trạng sức khỏe và mục tiêu của từng người, giúp tăng khả năng thành công khi cai thuốc.</li>

                        <li><strong>Tư vấn từ chuyên gia:</strong> Thành viên sẽ được hỗ trợ liên tục từ các bác sĩ, chuyên gia tâm lý và huấn luyện viên thông qua các buổi tư vấn trực tuyến và hội thảo.</li>

                        <li><strong>Cộng đồng hỗ trợ tích cực:</strong> Gia nhập một cộng đồng tích cực, nơi bạn có thể chia sẻ trải nghiệm, động viên lẫn nhau và cùng nhau tiến bộ.</li>

                        <li><strong>Tài liệu và công cụ hữu ích:</strong> Truy cập kho tài liệu bao gồm bài viết, video và hướng dẫn chi tiết về tác hại của thuốc lá, cách vượt qua cơn thèm và giữ vững động lực.</li>
                    </ol>

                </div>

            </section>

            <section class="story">
                <div class="content">
                    <<h3>Câu Chuyện Của Chúng Tôi</h3>
                    <p style="font-size: 17px">BBFB được thành lập với niềm đam mê giúp mọi người vượt qua cơn nghiện thuốc lá.
                        Thông qua các kế hoạch cá nhân hóa, sự hướng dẫn từ chuyên gia và cách tiếp cận lấy cộng đồng làm trung tâm,
                        chúng tôi nỗ lực tạo ra tác động lâu dài đến sức khỏe và chất lượng cuộc sống.</p>

                </div>
                <div class="image">
                    <img src="images/aboutUs/cay.png" alt="Our Story">
                </div>
            </section>
        </main>

        <%@include file="information/footer.jspf" %>
    </body>

</html>