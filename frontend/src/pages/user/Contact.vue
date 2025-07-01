<template>
    <div class="row align-items-center bg-warning bg-opacity-10 py-5">
        <div class="col-lg-6 mb-4 mb-lg-0">
            <h1 class="fw-bold mb-4">Liên Hệ Với Chúng Tôi</h1>
            <p class="lead">Hãy cho chúng tôi biết bạn cần loại bánh gì. FShop luôn sẵn sàng phục vụ bạn với
                những chiếc bánh tươi ngon, an toàn và ngọt ngào mỗi ngày!</p>
        </div>
        <div class="col-lg-6">
            <img src="https://images.unsplash.com/photo-1578985545062-69928b1d9587?auto=format&fit=crop&w=1200&h=500&q=80"
                alt="Liên hệ với chúng tôi" class="img-fluid rounded shadow">
        </div>
    </div>

    <!-- Thông tin liên hệ  -->
    <div class="row mb-2">
        <div class="col-lg-12">
            <div class="card border-0 ">
                <div class="card-body">
                    <h2 class="h4 fw-bold mb-4 position-relative">
                        <span class="position-relative">
                            Liên Hệ Với FShop
                            <span class="position-absolute start-0 bottom-0 w-50"
                                style="height: 3px; background-color: var(--bs-warning);"></span>
                        </span>
                    </h2>

                    <div class="py-4">
                        <p class="lead">Bạn có thể liên hệ với chúng tôi qua nhiều hình thức khác nhau:</p>
                        <div class="row mb-4">
                            <div class="col-md-6 mb-3">
                                <div class="d-flex align-items-center">
                                    <i class="bi bi-geo-alt-fill text-warning fs-4 me-3"></i>
                                    <div>
                                        <h5 class="mb-1">Địa chỉ</h5>
                                        <p class="mb-0">456 Đường Bánh Ngọt, Quận 2, TP.HCM</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 mb-3">
                                <div class="d-flex align-items-center">
                                    <i class="bi bi-telephone-fill text-warning fs-4 me-3"></i>
                                    <div>
                                        <h5 class="mb-1">Điện thoại</h5>
                                        <a href="tel:+84987654321" class="text-decoration-none">+84 987 654 321</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 mb-3">
                                <div class="d-flex align-items-center">
                                    <i class="bi bi-envelope-fill text-warning fs-4 me-3"></i>
                                    <div>
                                        <h5 class="mb-1">Email</h5>
                                        <a href="mailto:contact@fshop.vn"
                                            class="text-decoration-none">contact@fshop.vn</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 mb-3">
                                <div class="d-flex align-items-center">
                                    <i class="bi bi-clock-fill text-warning fs-4 me-3"></i>
                                    <div>
                                        <h5 class="mb-1">Giờ mở cửa</h5>
                                        <p class="mb-0">Thứ 2 - CN: 7:00 - 20:00</p>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bản đồ -->
    <div class="row mb-2">
        <div class="col-12">
            <div class="map-container position-relative w-100" style="height: 450px;">
                <iframe class="position-absolute top-0 start-0 w-100 h-100 border-0"
                    src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3929.4204946227965!2d105.75822739999997!3d9.982081500000001!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31a08906415c355f%3A0x416815a99ebd841e!2sFPT%20Polytechnic%20College!5e0!3m2!1sen!2s!4v1750891977935!5m2!1sen!2s"
                    width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy"
                    referrerpolicy="no-referrer-when-downgrade"></iframe>

            </div>
        </div>
    </div>


    <!-- FAQ -->
    <div class="row justify-content-center mb-2">
        <div class="col-lg-8 text-center">
            <h2 class="fw-bold mb-4 position-relative">
                <span class="position-relative">
                    Câu Hỏi Thường Gặp
                    <span class="position-absolute start-0 bottom-0 w-100"
                        style="height: 3px; background-color: var(--bs-warning);"></span>
                </span>
            </h2>
            <p class="lead">Những thắc mắc phổ biến từ khách hàng của FShop</p>
        </div>
    </div>

    <div class="row justify-content-center">
        <div class="col-lg-8">
            <div class="accordion" id="faqAccordion">
                <!-- FAQ 1 -->
                <div class="accordion-item border-0 mb-3 shadow-sm">
                    <h3 class="accordion-header" id="headingOne">
                        <button class="accordion-button fw-bold" type="button" data-bs-toggle="collapse"
                            data-bs-target="#collapseOne">
                            Làm thế nào để đặt mua bánh tại FShop?
                        </button>
                    </h3>
                    <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#faqAccordion">
                        <div class="accordion-body">
                            Bạn có thể đặt hàng qua website, gọi điện hoặc đến trực tiếp cửa hàng. Đối với các loại
                            bánh đặt theo yêu cầu, vui lòng đặt trước ít nhất 24 giờ.
                        </div>
                    </div>
                </div>
                <!-- FAQ 2 -->
                <div class="accordion-item border-0 mb-3 shadow-sm">
                    <h3 class="accordion-header" id="headingTwo">
                        <button class="accordion-button collapsed fw-bold" type="button" data-bs-toggle="collapse"
                            data-bs-target="#collapseTwo">
                            FShop có giao hàng tận nơi không?
                        </button>
                    </h3>
                    <div id="collapseTwo" class="accordion-collapse collapse" data-bs-parent="#faqAccordion">
                        <div class="accordion-body">
                            Có, chúng tôi giao hàng nhanh trong nội thành và gửi hàng tỉnh qua các đơn vị vận
                            chuyển. Giao miễn phí cho đơn hàng từ 300.000₫ trong khu vực nội thành.
                        </div>
                    </div>
                </div>
                <!-- FAQ 3 -->
                <div class="accordion-item border-0 mb-3 shadow-sm">
                    <h3 class="accordion-header" id="headingThree">
                        <button class="accordion-button collapsed fw-bold" type="button" data-bs-toggle="collapse"
                            data-bs-target="#collapseThree">
                            Bánh của FShop có thể bảo quản bao lâu?
                        </button>
                    </h3>
                    <div id="collapseThree" class="accordion-collapse collapse" data-bs-parent="#faqAccordion">
                        <div class="accordion-body">
                            Các loại bánh tươi nên dùng trong 1–3 ngày và bảo quản lạnh. Bánh quy hoặc kẹo có thể
                            dùng trong 1–2 tháng tùy loại.
                        </div>
                    </div>
                </div>
                <!-- FAQ 4 -->
                <div class="accordion-item border-0 mb-3 shadow-sm">
                    <h3 class="accordion-header" id="headingFour">
                        <button class="accordion-button collapsed fw-bold" type="button" data-bs-toggle="collapse"
                            data-bs-target="#collapseFour">
                            Tôi có thể hủy đơn hàng bánh không?
                        </button>
                    </h3>
                    <div id="collapseFour" class="accordion-collapse collapse" data-bs-parent="#faqAccordion">
                        <div class="accordion-body">
                            Bạn có thể hủy đơn nếu bánh chưa được giao. Đối với bánh đặt riêng, vui lòng liên hệ sớm
                            trước khi chế biến để được hoàn tiền một phần.
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script setup lang="ts"></script>
