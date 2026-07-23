<script setup lang="ts">
import { computed, nextTick, ref, watch } from 'vue'
import { RouterLink, useRoute } from 'vue-router'
import ArticleCard from '../components/ArticleCard.vue'
import { articleFilters, type ArticleFilter, siteContent } from '../data/siteContent'

const route = useRoute()
const keyword = ref('')
const selectedTrack = ref<ArticleFilter>('全部')
const searchInput = ref<HTMLInputElement | null>(null)

const filteredArticles = computed(() => {
  const text = keyword.value.trim().toLowerCase()

  return siteContent.articles.filter((article) => {
    const matchesTrack = selectedTrack.value === '全部' || article.track === selectedTrack.value
    const matchesKeyword =
      !text ||
      article.title.toLowerCase().includes(text) ||
      article.summary.toLowerCase().includes(text) ||
      article.tags.some((tag) => tag.toLowerCase().includes(text))

    return matchesTrack && matchesKeyword
  })
})

watch(
  () => route.query.search,
  async (value) => {
    if (!value) return
    await nextTick()
    searchInput.value?.focus()
  },
  { immediate: true },
)
</script>

<template>
  <main class="inner-page page-reveal">
    <RouterLink class="back-link" to="/">
      <span aria-hidden="true">←</span>
      Back
    </RouterLink>

    <header class="inner-heading">
      <h1>Blog</h1>
      <p>技术、建站和生活记录。内容会慢慢增加，先保证每一篇都值得留下来。</p>
    </header>

    <div class="blog-layout">
      <section class="blog-main" aria-label="文章列表">
        <div class="blog-list-meta">
          <span>Showing {{ filteredArticles.length }} of {{ siteContent.articles.length }} posts</span>
          <span>{{ selectedTrack === '全部' ? 'All notes' : selectedTrack }}</span>
        </div>

        <div v-if="filteredArticles.length" class="blog-card-list">
          <ArticleCard v-for="article in filteredArticles" :key="article.id" :article="article" />
        </div>

        <div v-else class="empty-state">
          <p>没有找到对应内容。</p>
          <button type="button" @click="keyword = ''; selectedTrack = '全部'">清除筛选</button>
        </div>
      </section>

      <aside class="blog-sidebar">
        <section class="sidebar-block">
          <h2>Tags</h2>
          <div class="filter-tags" role="group" aria-label="文章分类">
            <button
              v-for="filterItem in articleFilters"
              :key="filterItem"
              type="button"
              :class="{ active: selectedTrack === filterItem }"
              @click="selectedTrack = filterItem"
            >
              {{ filterItem }}
            </button>
          </div>
        </section>

        <section class="sidebar-block">
          <label for="article-search">Search</label>
          <div class="sidebar-search">
            <svg aria-hidden="true" viewBox="0 0 24 24">
              <circle cx="11" cy="11" r="7" />
              <path d="m20 20-4-4" />
            </svg>
            <input
              id="article-search"
              ref="searchInput"
              v-model="keyword"
              type="search"
              placeholder="标题、摘要或标签"
            />
          </div>
        </section>
      </aside>
    </div>
  </main>
</template>
