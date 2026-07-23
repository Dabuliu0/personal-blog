<script setup lang="ts">
import { computed } from 'vue'
import type { Article } from '../data/siteContent'

const props = defineProps<{
  article: Article
  compact?: boolean
}>()

const formattedDate = computed(() => {
  const [year, month, day] = props.article.publishedAt.split('-')
  return props.compact ? `${year}年${Number(month)}月${Number(day)}日` : `${year}.${month}.${day}`
})
</script>

<template>
  <article class="article-card" :class="{ compact }" tabindex="0">
    <div class="article-card-content">
      <time :datetime="article.publishedAt">{{ formattedDate }}</time>
      <div class="article-card-main">
        <h3>{{ article.title }}</h3>
        <ul v-if="!compact" class="tag-list" aria-label="文章标签">
          <li v-for="tag in article.tags" :key="tag">{{ tag }}</li>
        </ul>
      </div>
      <svg class="article-arrow" aria-hidden="true" viewBox="0 0 24 24">
        <path class="article-arrow-line" d="M5 12h13" />
        <path d="m13 6 6 6-6 6" />
      </svg>
    </div>
  </article>
</template>
