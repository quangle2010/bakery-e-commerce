<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import axiosClient from '../../util/axiosClient'
const router = useRouter();
const route = useRoute();
const countdown = ref(5)
const orderId = ref(null)
const vnp_ResponseCode = ref(route.query.vnp_ResponseCode?.toString() || '');
onMounted(() => {
    const interval = setInterval(() => {
        if (countdown.value > 1) {
            countdown.value--
        } else {
            clearInterval(interval)
            router.push('/')
        }
    }, 1000)
})

onMounted(async () => {
    try {
        orderId.value=localStorage.getItem("orderId");
        const param = {
            orderId: orderId.value,
            vnpResponseCode: vnp_ResponseCode.value
        }
        const paymentStatus = await axiosClient.post('/user/return-payment', null, {
            params: param
        });
        console.log(paymentStatus)
        if (paymentStatus.status === 200 && paymentStatus.data.status) {
            localStorage.removeItem('orderId')
        } else {

        }
    } catch (error) {
        console.error(error);
    }

})



const goHome = () => {
    router.push('/')
}
</script>

<template>
    <div class="payment-success">
        <div class="card">
            <img src="https://cdn-icons-png.flaticon.com/512/869/869869.png" alt="cake icon" class="cake-icon" />
            <h2 class="title">Ngon lành! 🎂</h2>
            <p class="message">Bạn đã thanh toán đơn hàng bánh ngọt thành công.</p>
            <p class="message">Bạn sẽ được chuyển về trang chủ sau <strong>{{ countdown }}</strong>.</p>
            <button class="home-button" @click="goHome">Quay về tiệm bánh</button>
        </div>
    </div>
</template>

<style scoped>
.payment-success {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background: linear-gradient(135deg, #ffe7e7, #fff4f0);
}

.card {
    background-color: #fffaf9;
    padding: 40px 30px;
    border-radius: 16px;
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
    text-align: center;
    width: 90%;
    max-width: 400px;
    animation: fadeInUp 0.6s ease-out;
}

.cake-icon {
    width: 80px;
    height: 80px;
    margin-bottom: 20px;
}

.title {
    color: #d9777f;
    font-family: 'Comic Sans MS', cursive, sans-serif;
    font-size: 24px;
    margin-bottom: 10px;
}

.message {
    color: #5c4033;
    font-size: 16px;
    margin: 6px 0;
    font-family: 'Segoe UI', sans-serif;
}

.home-button {
    margin-top: 20px;
    background-color: #f9a8d4;
    color: #fff;
    padding: 10px 20px;
    font-size: 16px;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.home-button:hover {
    background-color: #ec4899;
}

@keyframes fadeInUp {
    0% {
        opacity: 0;
        transform: translateY(30px);
    }

    100% {
        opacity: 1;
        transform: translateY(0);
    }
}
</style>
