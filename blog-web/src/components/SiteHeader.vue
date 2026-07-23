<script setup lang="ts">
import { onBeforeUnmount, onMounted, ref } from 'vue'
import { RouterLink } from 'vue-router'
import { siteContent } from '../data/siteContent'

const isScrolled = ref(false)
const menuOpen = ref(false)

const syncHeader = () => {
  isScrolled.value = window.scrollY > 16
}

const closeMenu = () => {
  menuOpen.value = false
}

onMounted(() => {
  syncHeader()
  window.addEventListener('scroll', syncHeader, { passive: true })
})

onBeforeUnmount(() => {
  window.removeEventListener('scroll', syncHeader)
})
</script>

<template>
  <header class="site-header" :class="{ 'is-scrolled': isScrolled, 'menu-open': menuOpen }">
    <RouterLink to="/" class="brand" :aria-label="`${siteContent.brand.name} 首页`" @click="closeMenu">
      {{ siteContent.brand.name }}
    </RouterLink>

    <div class="header-actions">
      <nav id="primary-navigation" class="menu" aria-label="主导航">
        <RouterLink to="/articles" @click="closeMenu">文章</RouterLink>
        <a href="/#projects" @click="closeMenu">项目</a>
        <RouterLink to="/about" @click="closeMenu">关于</RouterLink>
      </nav>

      <RouterLink
        class="icon-link"
        :to="{ path: '/articles', query: { search: '1' } }"
        aria-label="搜索文章"
        title="搜索文章"
        @click="closeMenu"
      >
        <svg aria-hidden="true" viewBox="0 0 24 24">
          <circle cx="11" cy="11" r="7" />
          <path d="m20 20-4-4" />
        </svg>
      </RouterLink>

      <a
        class="icon-link desktop-only"
        :href="siteContent.profile.github"
        target="_blank"
        rel="noreferrer"
        aria-label="访问 GitHub"
        title="GitHub"
      >
        <svg aria-hidden="true" viewBox="0 0 24 24">
          <path d="M15 22v-4a4.8 4.8 0 0 0-1-3.5c3.3-.4 6.8-1.6 6.8-7.4A5.8 5.8 0 0 0 19.3 3 5.4 5.4 0 0 0 19.1.2S17.9-.2 15 1.7a13.4 13.4 0 0 0-6 0C6.1-.2 4.9.2 4.9.2A5.4 5.4 0 0 0 4.7 3a5.8 5.8 0 0 0-1.5 4.1c0 5.8 3.5 7 6.8 7.4A4.8 4.8 0 0 0 9 18v4" />
        </svg>
      </a>

      <button
        class="menu-toggle"
        type="button"
        :aria-expanded="menuOpen"
        aria-controls="primary-navigation"
        :aria-label="menuOpen ? '关闭导航' : '打开导航'"
        @click="menuOpen = !menuOpen"
      >
        <svg v-if="!menuOpen" aria-hidden="true" viewBox="0 0 24 24">
          <path d="M4 7h16M4 12h16M4 17h16" />
        </svg>
        <svg v-else aria-hidden="true" viewBox="0 0 24 24">
          <path d="m6 6 12 12M18 6 6 18" />
        </svg>
      </button>
    </div>
  </header>
</template>
