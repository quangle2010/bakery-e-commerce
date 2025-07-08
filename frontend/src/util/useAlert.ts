import Swal from 'sweetalert2';

export function showSuccess(title='xin chào',message = 'Bạn vừa lưu dữ liệu thành công!') {
  Swal.fire({
    icon: 'success',
    title: title,
    text: message,
    timer: 3000,
    showConfirmButton: false,
    timerProgressBar: true,
  });
}

export function showError(message = 'Có lỗi xảy ra, vui lòng thử lại.') {
  Swal.fire({
    icon: 'error',
    title: 'Lỗi',
    text: message,
    timer: 3000,
    showConfirmButton: false,
    timerProgressBar: true,
  });
}

export function showWarning(message = 'Bạn chưa điền đủ thông tin.') {
  Swal.fire({
    icon: 'warning',
    title: 'Cảnh báo',
    text: message,
    timer: 3000,
    showConfirmButton: false,
    timerProgressBar: true,
  });
}

export function showInfo(message = 'Thông tin cập nhật.') {
  Swal.fire({
    icon: 'info',
    title: 'Thông tin',
    text: message,
    timer: 3000,
    showConfirmButton: false,
    timerProgressBar: true,
  });
}
