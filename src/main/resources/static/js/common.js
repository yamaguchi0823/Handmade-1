function toggleMenu(){
    const nav = document.getElementById("nav-menu");
    const overlay = document.getElementById("menu-overlay");

    nav.classList.toggle("open");
    overlay.classList.toggle("open");
}

// メニューを閉じる
function closeMenu(){
    const nav = document.getElementById("nav-menu");
    const overlay = document.getElementById("menu-overlay");

    nav.classList.remove("open");
    overlay.classList.remove("open");
}