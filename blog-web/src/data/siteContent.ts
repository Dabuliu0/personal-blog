export const articleFilters = ['全部', '技术', '生活', '建站'] as const

export type ArticleFilter = (typeof articleFilters)[number]
export type ArticleTrack = Exclude<ArticleFilter, '全部'>

export type Article = {
  id: number
  title: string
  summary: string
  track: ArticleTrack
  tags: string[]
  publishedAt: string
  readingTime: string
}

type JourneyItem = {
  period: string
  title: string
  description: string
}

type ProjectItem = {
  name: string
  summary: string
  status: string
  tone: 'rose' | 'blue'
}

type SkillGroup = {
  title: string
  items: string[]
}

type AboutSection = {
  id: string
  title: string
  paragraphs: string[]
}

export const siteContent: {
  brand: {
    name: string
    note: string
  }
  profile: {
    name: string
    alias: string
    role: string
    location: string
    intro: string
    avatar: string
    github: string
    email: string
    contactLabel: string
  }
  homeAbout: {
    role: string
    paragraphs: string[]
  }
  articles: Article[]
  journey: JourneyItem[]
  projects: ProjectItem[]
  skillGroups: SkillGroup[]
  aboutSections: AboutSection[]
  writingPrinciples: string[]
} = {
  brand: {
    name: 'Shijiu Notes',
    note: 'Notes on backend and building',
  },
  profile: {
    name: '石九',
    alias: 'Shijiu',
    role: 'Java Backend Learner / Personal Builder',
    location: 'China',
    intro: '正在学习 Java 后端，也在搭建一个愿意长期维护的个人空间。',
    avatar: '/avatar.svg',
    github: 'https://github.com/',
    email: 'hello@example.com',
    contactLabel: 'Get in Touch',
  },
  homeAbout: {
    role: 'Backend Learner / Personal Builder',
    paragraphs: [
      '我现在主要在学习 Java 后端开发，也在持续打磨这个博客。这里会收纳技术理解、项目取舍，以及一些不需要被包装得很宏大的生活片段。',
      '我更在意一件事能不能慢慢做深，而不是短时间内看起来拥有很多。',
    ],
  },
  articles: [
    {
      id: 1,
      title: '把 Spring Boot 学习笔记写成自己真的会回看的东西',
      summary: '先把一个概念、一段流程、一个踩坑点写明白，慢慢积累出自己的知识密度。',
      track: '技术',
      tags: ['Spring Boot', '学习方法'],
      publishedAt: '2026-07-18',
      readingTime: '8 min',
    },
    {
      id: 2,
      title: '这个博客为什么要重做前端，而不是继续堆功能',
      summary: '如果页面没有自己的秩序，再多功能也只是一个可以使用的壳。',
      track: '建站',
      tags: ['设计取舍', 'Vue 3'],
      publishedAt: '2026-07-16',
      readingTime: '6 min',
    },
    {
      id: 3,
      title: '最近在补 Java 并发，最大的感受是不能只背术语',
      summary: '真正写下来时，才知道自己对线程、锁和可见性的理解卡在哪一层。',
      track: '技术',
      tags: ['Java', '并发'],
      publishedAt: '2026-07-11',
      readingTime: '9 min',
    },
    {
      id: 4,
      title: '我想保留生活内容，因为人不是只由技术栈组成的',
      summary: '散步、阅读和偶尔重新开始，会让一个站真正活起来。',
      track: '生活',
      tags: ['写作', '日常'],
      publishedAt: '2026-07-09',
      readingTime: '5 min',
    },
    {
      id: 5,
      title: '做个人项目时，我越来越在意能不能长期维护',
      summary: '真正有价值的不是一时做出来，而是半年后还愿不愿意回来继续写。',
      track: '建站',
      tags: ['工程习惯', '维护性'],
      publishedAt: '2026-07-05',
      readingTime: '7 min',
    },
    {
      id: 6,
      title: '最近想把阅读速度放慢一点，给自己留出沉淀的空间',
      summary: '不是每一次输入都要马上转化成产出。',
      track: '生活',
      tags: ['阅读', '节奏'],
      publishedAt: '2026-07-02',
      readingTime: '4 min',
    },
  ],
  journey: [
    {
      period: '2025 — NOW',
      title: 'Independent Learning',
      description: '围绕 Java、Spring Boot、数据库与接口设计建立一条连续的后端学习主线。',
    },
  ],
  projects: [
    {
      name: 'Personal Blog',
      summary: '一个前后端分离的长期个人博客项目。现在先把表达、排版和内容结构做稳。',
      status: 'In progress',
      tone: 'rose',
    },
    {
      name: 'Backend Notes',
      summary: '收纳 Java、数据库、安全和接口设计中真正遇到的问题，而不是知识点清单。',
      status: 'Growing slowly',
      tone: 'blue',
    },
  ],
  skillGroups: [
    {
      title: 'Languages',
      items: ['Java', 'TypeScript', 'SQL', 'Shell'],
    },
    {
      title: 'Frontend',
      items: ['Vue 3', 'Vite', 'CSS'],
    },
    {
      title: 'Backend',
      items: ['Spring Boot', 'JWT', 'MySQL', 'REST'],
    },
  ],
  aboutSections: [
    {
      id: 'where-i-am',
      title: 'Where I Am',
      paragraphs: [
        '你好，我是石九。目前正在学习 Java 后端开发，也在用真实项目把分散的知识慢慢连接起来。',
        '比起给自己贴很多标签，我更愿意记录现在确实在做什么、理解到了哪一步，以及还有哪些问题没有想清楚。',
      ],
    },
    {
      id: 'learning',
      title: 'What I Am Learning',
      paragraphs: [
        '现阶段的主线是 Java、Spring Boot、MySQL、权限控制和接口设计。我希望不只是会调用框架，而是逐渐理解请求经过系统时发生了什么。',
        '前端不是我的主要方向，但这个博客让我开始认真对待信息结构、排版和用户感受。一个项目的表达方式，同样属于工程质量。',
      ],
    },
    {
      id: 'about-blog',
      title: 'About This Blog',
      paragraphs: [
        '这个博客不会只放看起来完整的教程。我也想保留踩坑记录、阶段总结、建站过程和生活文字，让它能真实反映一段时间里的变化。',
        '功能可以慢慢补，内容也可以慢慢长出来，但我希望每次回到这里，都还能认出这是自己的空间。',
      ],
    },
  ],
  writingPrinciples: [
    '先写自己真正理解的部分，不靠堆概念制造充实感。',
    '技术可以严谨，表达不必故作高深。',
    '如果一句话谁都能写出来，它大概还不够接近自己。',
    '允许技术与生活并排存在。',
  ],
}
