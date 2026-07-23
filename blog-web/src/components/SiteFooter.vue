<script setup lang="ts">
import { siteContent } from '../data/siteContent'

const year = new Date().getFullYear()
</script>

<template>
  <footer class="site-footer">
    <div class="footer-copy">
      <span>在技术、项目和生活之间慢慢建立自己的坐标。</span>
      <span>© {{ year }} {{ siteContent.profile.alias }}</span>
    </div>
    <p>Built with Vue</p>
  </footer>
</template>
