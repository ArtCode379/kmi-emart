package kmiemart.consult.app.data.repository

import kmiemart.consult.app.R
import kmiemart.consult.app.data.model.ServiceModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ServiceRepository {
    private val services: List<ServiceModel> = listOf(
        ServiceModel(
            id = 1,
            name = "Cybersecurity Consulting",
            description = "Comprehensive security audits, penetration testing, and vulnerability assessments to protect your digital infrastructure from evolving threats.",
            price = "From $200",
            duration = "3h session",
            category = "Cybersecurity",
            imageRes = R.drawable.service_cyber,
            features = listOf(
                "Penetration testing and vulnerability scanning",
                "Security policy development and review",
                "Incident response planning",
                "Employee security awareness training",
                "Compliance gap analysis",
            ),
        ),
        ServiceModel(
            id = 2,
            name = "Cloud Architecture",
            description = "Design and implement scalable cloud solutions on AWS, Azure, or GCP. Migration planning, cost optimization, and infrastructure as code.",
            price = "From $250",
            duration = "4h session",
            category = "Cloud",
            imageRes = R.drawable.service_cloud,
            features = listOf(
                "Multi-cloud strategy and design",
                "Migration planning and execution",
                "Cost optimization and FinOps",
                "Infrastructure as Code implementation",
                "High availability architecture",
            ),
        ),
        ServiceModel(
            id = 3,
            name = "Business Process Optimization",
            description = "Analyze and streamline your workflows with data-driven strategies. Reduce operational costs and boost team productivity.",
            price = "From $180",
            duration = "2h session",
            category = "Optimization",
            imageRes = R.drawable.service_bpo,
            features = listOf(
                "Workflow analysis and mapping",
                "Bottleneck identification and resolution",
                "Automation opportunity assessment",
                "KPI framework design",
            ),
        ),
        ServiceModel(
            id = 4,
            name = "IT Strategy Development",
            description = "Align your technology roadmap with business goals. Long-term planning, vendor evaluation, and digital transformation consulting.",
            price = "From $300",
            duration = "3h session",
            category = "Strategy",
            imageRes = R.drawable.service_strategy,
            features = listOf(
                "Technology roadmap creation",
                "Vendor evaluation and selection",
                "Digital transformation planning",
                "IT budget optimization",
                "Stakeholder alignment workshops",
            ),
        ),
        ServiceModel(
            id = 5,
            name = "Digital Systems Audit",
            description = "Thorough assessment of your existing IT systems, software licenses, and infrastructure health. Get a clear picture of your tech landscape.",
            price = "From $150",
            duration = "2h session",
            category = "Audit",
            imageRes = R.drawable.service_audit,
            features = listOf(
                "Infrastructure health assessment",
                "Software license compliance review",
                "Performance benchmarking",
                "Risk identification report",
            ),
        ),
        ServiceModel(
            id = 6,
            name = "Data Analytics & Insights",
            description = "Transform raw data into actionable business intelligence. Dashboard design, KPI tracking, and predictive analytics implementation.",
            price = "From $220",
            duration = "3h session",
            category = "Analytics",
            imageRes = R.drawable.service_analytics,
            features = listOf(
                "Dashboard design and implementation",
                "KPI tracking setup",
                "Predictive analytics models",
                "Data pipeline architecture",
            ),
        ),
        ServiceModel(
            id = 7,
            name = "Network Infrastructure",
            description = "Design, deploy, and maintain robust network solutions. From office LANs to enterprise WAN architectures with 99.9% uptime guarantee.",
            price = "From $280",
            duration = "4h session",
            category = "Cloud",
            imageRes = R.drawable.service_network,
            features = listOf(
                "Network architecture design",
                "LAN and WAN deployment",
                "Network security hardening",
                "Performance monitoring setup",
                "Disaster recovery planning",
            ),
        ),
        ServiceModel(
            id = 8,
            name = "Compliance & Governance",
            description = "Navigate GDPR, ISO 27001, SOC 2, and industry-specific regulations. Risk assessment frameworks and compliance roadmaps.",
            price = "From $200",
            duration = "2h session",
            category = "Audit",
            imageRes = R.drawable.service_compliance,
            features = listOf(
                "GDPR and data protection compliance",
                "ISO 27001 readiness assessment",
                "SOC 2 audit preparation",
                "Risk assessment frameworks",
            ),
        ),
    )

    fun observeAll(): Flow<List<ServiceModel>> {
        return flowOf(services)
    }

    fun observeById(id: Int): Flow<ServiceModel?> {
        val service = services.firstOrNull { service -> service.id == id }
        return flowOf(service)
    }

    fun getById(id: Int): ServiceModel? {
        return services.firstOrNull { service -> service.id == id }
    }
}
