<script setup lang="ts">
import { RouterLink } from 'vue-router'
import ArticleCard from '../components/ArticleCard.vue'
import ProfileHero from '../components/ProfileHero.vue'
import { siteContent } from '../data/siteContent'

const recentArticles = siteContent.articles.slice(0, 6)
</script>

<template>
  <main class="home-page">
    <ProfileHero />

    <div class="home-content page-reveal page-reveal-late">
      <section class="home-section">
        <div class="home-section-label">
          <h2>About</h2>
        </div>
        <div class="home-section-body about-summary">
          <p class="lead-copy">{{ siteContent.homeAbout.role }}</p>
          <p v-for="paragraph in siteContent.homeAbout.paragraphs" :key="paragraph">
            {{ paragraph }}
          </p>
          <RouterLink class="small-link" to="/about">More about me</RouterLink>
        </div>
      </section>

      <section class="home-section">
        <div class="home-section-label">
          <h2>Posts</h2>
        </div>
        <div class="home-section-body">
          <div class="home-post-list">
            <ArticleCard
              v-for="article in recentArticles"
              :key="article.id"
              :article="article"
              compact
            />
          </div>
          <RouterLink class="small-link" to="/articles">
            More posts
            <span aria-hidden="true">→</span>
          </RouterLink>
        </div>
      </section>

      <section class="home-section">
        <div class="home-section-label">
          <h2>Journey</h2>
        </div>
        <div class="home-section-body">
          <article v-for="item in siteContent.journey" :key="item.period" class="journey-card">
            <h3>{{ item.title }}</h3>
            <p>{{ item.description }}</p>
            <time>{{ item.period }}</time>
          </article>
        </div>
      </section>

      <section id="projects" class="home-section">
        <div class="home-section-label">
          <h2>Project List</h2>
        </div>
        <div class="home-section-body project-grid">
          <article
            v-for="project in siteContent.projects"
            :key="project.name"
            class="project-card"
            :class="`project-card-${project.tone}`"
          >
            <div class="project-art" aria-hidden="true">
              <span></span>
              <span></span>
              <span></span>
            </div>
            <div class="project-copy">
              <p>{{ project.status }}</p>
              <h3>{{ project.name }}</h3>
              <p>{{ project.summary }}</p>
            </div>
          </article>
        </div>
      </section>

      <section class="home-section">
        <div class="home-section-label">
          <h2>Skills</h2>
        </div>
        <div class="home-section-body skills-list">
          <div v-for="group in siteContent.skillGroups" :key="group.title" class="skill-row">
            <h3>{{ group.title }}</h3>
            <ul class="tag-list">
              <li v-for="item in group.items" :key="item">{{ item }}</li>
            </ul>
          </div>
        </div>
      </section>
    </div>
  </main>
</template>
