<template>
  <footer class="footer">
    <div class="container">
      <div class="row">
        <!-- About -->
        <div class="col-md-3 mb-2">
          <h5 class="footer-heading">FSHOP</h5>
          <p class="footer-text">Chúng tôi chuyên cung cấp các loại bánh kem, bánh quy, kẹo
            dẻo và bánh trung thu chất lượng cao, mang đến hương vị ngọt ngào
            và dịch vụ tận tâm hàng đầu.</p>
          <div class="social-icons">
            <a href="#" class="social-icon"><i class="bi bi-facebook"></i></a>
            <a href="#" class="social-icon"><i class="bi bi-instagram"></i></a>
            <a href="#" class="social-icon"><i class="bi bi-twitter-x"></i></a>
            <a href="#" class="social-icon"><i class="bi bi-youtube"></i></a>
          </div>

        </div>

        <!-- Quick Links -->
        <div class="col-md-3 mb-2">
          <h5 class="footer-heading">Liên kết nhanh</h5>
          <ul class="footer-links">
            <li><a href="/">Trang chủ</a></li>
            <li><a href="/products">Sản phẩm</a></li>
            <li><a href="/about-us">Về chúng tôi</a></li>
            <li><a href="/contact">Liên hệ</a></li>
          </ul>
        </div>

        <!-- Categories -->
        <div class="col-md-3 mb-2">
          <h5 class="footer-heading">Danh mục sản phẩm</h5>
          <ul class="footer-links">
            <li><a href="/category/cakes">Bánh kem</a></li>
            <li><a href="/category/cookies">Bánh quy</a></li>
            <li><a href="/category/candies">Kẹo dẻo</a></li>
            <li><a href="/category/mooncakes">Bánh trung thu</a></li>
          </ul>
        </div>

        <!-- Contact -->
        <div class="col-md-3 mb-2">
          <h5 class="footer-heading">Liên hệ</h5>
          <ul class="contact-info">
            <li><i class="bi bi-geo-alt"></i> 123 Đường ABC, Quận 1, TP.HCM</li>
            <li><i class="bi bi-envelope"></i> nhomone@gmail.com</li>
            <li><i class="bi bi-telephone"></i> + 84915876498</li>
            <li><i class="bi bi-clock"></i> Thứ 2 - Chủ nhật: 8:00 - 21:00</li>
          </ul>

        </div>
      </div>

    
    </div>
  </footer>
</template>

<script setup lang="ts">
// Footer component
</script>

<style scoped>
.footer {
  background-color: #fff8f0;
  color: #333;
  padding: 2rem 0;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.footer-heading {
  font-weight: 600;
  text-transform: uppercase;
  margin-bottom: 1.5rem;
  font-size: 1.1rem;
  position: relative;
  padding-bottom: 0.75rem;
  color: #333;
}

.footer-heading::after {
  content: '';
  position: absolute;
  left: 0;
  bottom: 0;
  width: 50px;
  height: 2px;
  background-color: #f8a5c2;
}

.footer-text {
  color: #666;
  line-height: 1.6;
  font-size: 0.95rem;
}

.social-icons {
  display: flex;
  margin-top: 1.5rem;
}

.social-icon {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 36px;
  height: 36px;
  border-radius: 50%;
  background-color: rgba(248, 165, 194, 0.2);
  color: #f8a5c2;
  margin-right: 0.75rem;
  transition: all 0.3s ease;
}

.social-icon:hover {
  background-color: #f8a5c2;
  color: #fff;
  transform: translateY(-3px);
}

.footer-links {
  list-style: none;
  padding: 0;
  margin: 0;
}

.footer-links li {
  margin-bottom: 0.75rem;
}

.footer-links a {
  color: #666;
  text-decoration: none;
  transition: color 0.3s ease;
  font-size: 0.95rem;
  display: inline-block;
  position: relative;
}

.footer-links a:hover {
  color: #f8a5c2;
  padding-left: 5px;
}

.contact-info {
  list-style: none;
  padding: 0;
  margin: 0;
}

.contact-info li {
  display: flex;
  align-items: flex-start;
  margin-bottom: 1rem;
  color: #666;
  font-size: 0.95rem;
}

.contact-info i {
  margin-right: 0.75rem;
  color: #f8a5c2;
  width: 16px;
  text-align: center;
}

.footer-bottom {
  margin-top: 2rem;
}

.footer-divider {
  border-color: rgba(0, 0, 0, 0.1);
  margin: 1.5rem 0;
}

.copyright {
  color: #666;
  font-size: 0.9rem;
  margin-bottom: 0;
}

.payment-methods {
  margin-bottom: 0;
}

.payment-icon {
  font-size: 1.5rem;
  color: #666;
  margin-left: 0.75rem;
  transition: color 0.3s ease;
}

.payment-icon:hover {
  color: #f8a5c2;
}

@media (max-width: 767px) {
  .footer {
    padding: 3rem 0 1.5rem;
    text-align: center;
  }

  .footer-heading::after {
    left: 50%;
    transform: translateX(-50%);
  }

  .social-icons {
    justify-content: center;
  }

  .contact-info li {
    justify-content: center;
  }

  .contact-info i {
    margin-right: 0.5rem;
  }

  .copyright,
  .payment-methods {
    text-align: center;
  }

  .payment-icon {
    margin: 0 0.375rem;
  }
}
</style>
