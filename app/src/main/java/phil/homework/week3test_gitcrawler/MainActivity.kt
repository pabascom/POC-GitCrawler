package phil.homework.week3test_gitcrawler

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import phil.homework.week3test_gitcrawler.model.repo.Repository
import phil.homework.week3test_gitcrawler.network.RetrofitHelper
import phil.homework.week3test_gitcrawler.util.toast

class MainActivity : AppCompatActivity(), RepositoryRecyclerViewFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: Repository?) {
        toast(item?.fullName)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        /*val repositoryRecyclerViewFragment = RepositoryRecyclerViewFragment.newInstance(1)

        btnGetRepos.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .add(R.id.repoListHolder, repositoryRecyclerViewFragment, "repoRecyclerViewFragment")
                .commit()
        }*/
    }
}
